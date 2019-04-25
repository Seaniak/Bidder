package web.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import web.entities.Bid;
import web.entities.SocketEvent;
import web.repositories.BidRepo;

import java.util.ArrayList;
import java.util.List;

@Service
public class BidService {

  @Autowired
  private BidRepo bidRepo;
  @Autowired
  private SocketService socketService;

  public List<Bid> getAuctionBids(Long auctionId) {
    List<Bid> bids = bidRepo.findAllByAuctionId(auctionId);
    return bids == null ? new ArrayList<>() : bids;
  }

  public List<Bid> getAllBids() {
    return bidRepo.findAll();
  }

  public Bid insertBid(Bid bid) {
    Bid bidFromDB = bidRepo.save(bid);

//    emits the new bid to all connected users
//    to update auction
    socketService.sendToAll(new SocketEvent("bid", bidFromDB), SocketEvent.class);
    return bidFromDB;
  }

  public void deleteBid(Bid bid) {
    bidRepo.delete(bid);
  }
}
