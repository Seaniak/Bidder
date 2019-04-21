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

  @Autowired
  private ImageService imageService;

  public Auction getAuctionById(Long id) { return auctionRepo.getAuctionById(id); }

  public List<Auction> getAllAuctions(){
    List<Auction> auctions = auctionRepo.findAll();
    for(Auction auction : auctions){
      auction.setImages(imageService.getAuctionImagePaths(auction.getId()));
    }
    return auctions;
  }

  public Auction insertAuction(Auction auction){
    return auctionRepo.save(auction);
  }

  public void deleteAuction(Auction auction){
    auctionRepo.delete(auction);
  }
}
