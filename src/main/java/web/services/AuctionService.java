package web.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import web.entities.Auction;
import web.repositories.AuctionRepo;

import java.util.List;

@Service
public class AuctionService {

  @Autowired
  private AuctionRepo auctionRepo;

  public List<Auction> getAllAuctions(){
    return auctionRepo.findAll();
  }

  public Auction insertAuction(Auction auction){
    return auctionRepo.save(auction);
  }

  public void deleteAuction(Auction auction){
    auctionRepo.delete(auction);
  }
}
