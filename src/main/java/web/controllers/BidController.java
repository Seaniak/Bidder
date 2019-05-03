package web.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import web.entities.Auction;
import web.entities.Bid;
import web.services.AuctionService;
import web.services.BidService;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.List;
import java.util.concurrent.atomic.AtomicBoolean;

@RestController
@RequestMapping("/api/bids")
public class BidController {
	@Autowired
	private BidService bidService;
	@Autowired
	private AuctionService auctionService;

	@GetMapping("/{id}")
	public Iterable<Bid> getOneBid(@PathVariable Long id) {
		List<Bid> bids = bidService.getAuctionBids(id);
		return bids;
	}

	@PostMapping
	public Bid publishBid(@RequestBody Bid bid) {
		AtomicBoolean correctBid = new AtomicBoolean(
				!bid.getUsername().equals(auctionService.getAuctionById(bid.getAuctionId()).getUsername()));
		if(correctBid.get()){
			if(Timestamp.valueOf(LocalDateTime.now()).after(auctionService.getAuctionById(bid.getAuctionId()).getEndTime())) {
				correctBid.set(false);
			}
		}
		if (correctBid.get()) bidService.getAuctionBids(bid.getAuctionId()).forEach(b -> {
			if (b.getSum() >= bid.getSum()) correctBid.set(false);
		});
		if (correctBid.get()){
			bid.setTime(Timestamp.valueOf(LocalDateTime.now()));
			correctBid.set(bidService.insertBid(bid) != null);
		}
		if(correctBid.get()) return bid;
		else return null;
	}
}
