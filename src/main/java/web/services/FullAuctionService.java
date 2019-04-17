package web.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import web.entities.Auction;
import web.entities.FullAuction;
import web.repositories.AuctionRepo;
import web.repositories.FullAuctionRepo;

import java.util.List;

@Service
public class FullAuctionService {

  @Autowired
  private FullAuctionRepo fullAuctionRepo;

  public List<FullAuction> getAllAuctions(){
    return fullAuctionRepo.findAll();
  }
}
