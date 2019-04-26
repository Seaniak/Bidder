package web.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import web.entities.Auction;
import web.entities.Thumbnail;
import web.repositories.AuctionRepo;

import java.util.List;

@Service
public class AuctionService {

  @Autowired
  private AuctionRepo auctionRepo;
  @Autowired
  private ThumbnailService thumbnailService;

  public Auction getAuctionById(Long id) {
    return auctionRepo.getAuctionById(id);
  }

  public List<Auction> getUserAuctions(String username) {
    return addThumbnails(
            auctionRepo.findAllByUsername(username));
  }

  public List<Auction> getAllAuctions() {
    return addThumbnails(
            auctionRepo.findAll());
  }

  private List<Auction> addThumbnails(List<Auction> auctions) {
    for (Auction auction : auctions) {
      Thumbnail thumbnail = thumbnailService.getAuctionThumbnail(auction.getId());
      if (thumbnail != null)
        auction.setThumbnail(thumbnail.getImage());
    }
    return auctions;
  }

  public List<Auction> getSearchResult(String searchQuery) {
    if(searchQuery.isEmpty()) return getAllAuctions();
    List<Auction> tempList = auctionRepo.findAllByAuctionConditionContaining(searchQuery);
    tempList.addAll(auctionRepo.findAllByUsername(searchQuery));
    tempList.addAll(auctionRepo.findAllByCategoryContaining(searchQuery));
    tempList.addAll(auctionRepo.findAllByDescriptionContaining(searchQuery));
    tempList.addAll(auctionRepo.findAllByTitleContaining(searchQuery));
    return addThumbnails(tempList);
  }

  public Auction insertAuction(Auction auction) {
    return auctionRepo.save(auction);
  }

  public void deleteAuction(Auction auction) {
    auctionRepo.delete(auction);
  }
}
