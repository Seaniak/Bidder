package web.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import web.entities.Bid;
import web.repositories.BidRepo;

import java.util.ArrayList;
import java.util.List;

@Service
public class BidService {

  @Autowired
  private BidRepo bidRepo;

  public List<Bid> getAuctionBids(Long auctionId) {
    List<Bid> bids = bidRepo.findAllByAuctionId(auctionId);
    return bids == null ? new ArrayList<>() : bids;
  }

  public List<Bid> getAllBids() {
    return bidRepo.findAll();
  }

  public Bid insertBid(Bid bid) {
    return bidRepo.save(bid);
  }

  public void deleteBid(Bid bid) {
    bidRepo.delete(bid);
  }
}
