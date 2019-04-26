package web.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import web.entities.Auction;
import web.entities.Bid;
import web.entities.Thumbnail;
import web.services.AuctionService;
import web.services.BidService;
import web.services.ImageService;
import web.services.ThumbnailService;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/api/bids")
public class BidController {
  @Autowired
  private BidService bidService;

  @GetMapping("/{id}")
  public Iterable<Bid> getOneBid(@PathVariable Long id) {
    List<Bid> bids = bidService.getAuctionBids(id);
    return bids;
  }

  @PostMapping
    public Bid publishAuction(@RequestBody Bid bid) {
    System.out.println(bid);
    bid.setTime(Timestamp.valueOf(LocalDateTime.now()));
    Bid bidFromDb = bidService.insertBid(bid);
    return bidFromDb;
  }
}
