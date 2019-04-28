package web.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import web.entities.Auction;
import web.entities.Bid;
import web.entities.Thumbnail;
import web.repositories.AuctionRepo;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
public class AuctionService {

  @Autowired
  private AuctionRepo auctionRepo;
  @Autowired
  private ThumbnailService thumbnailService;
  @Autowired
  private BidService bidService;
  @Autowired
  private ImageService imageService;

  public Auction getAuctionById(Long id) {
    return auctionRepo.getAuctionById(id);
  }

  public List<Auction> getUserAuctions(String username) {
    return addThumbnails(
            auctionRepo.findAllByUsername(username));
  }

  public List<Auction> getAllAuctions() {
    return addImageLinks(addBids(addThumbnails(auctionRepo.findAll())));
  }

  private List<Auction> addThumbnails(List<Auction> auctions) {
    for (Auction auction : auctions) {
      Thumbnail thumbnail = thumbnailService.getAuctionThumbnail(auction.getId());
      if (thumbnail != null)
        auction.setThumbnail(thumbnail.getImage());
    }
    return auctions;
  }

  private List<Auction> addBids(List<Auction> auctions) {
    for (Auction auction : auctions) {
      List<Bid> bids = bidService.getAuctionBids(auction.getId());
      auction.setBids(bids);
    }
    return auctions;
  }

  private List<Auction> addImageLinks(List<Auction> auctions) {
    for (Auction auction : auctions) {
      List<String> images = imageService.getAuctionImageData(auction.getId());
      auction.setImages(images);
    }
    return auctions;
  }

  public List<Auction> getSearchResult(String searchQuery) {
    if(searchQuery.isEmpty()) return getAllAuctions();
    Set<Auction> filteredRes = new HashSet<>();
    List<Auction> finalResult = new ArrayList<>();
    List<Auction> searchRes = auctionRepo.findAllByAuctionConditionContaining(searchQuery);
    searchRes.addAll(auctionRepo.findAllByUsername(searchQuery));
    searchRes.addAll(auctionRepo.findAllByCategoryContaining(searchQuery));
    searchRes.addAll(auctionRepo.findAllByDescriptionContaining(searchQuery));
    searchRes.addAll(auctionRepo.findAllByTitleContaining(searchQuery));
    filteredRes.addAll(searchRes);
    finalResult.addAll(filteredRes);
    finalResult.forEach(auction -> {
      List<String> images = imageService.getAuctionImageData(auction.getId());
      auction.setImages(images);
      List<Bid> bids = bidService.getAuctionBids(auction.getId());
      auction.setBids(bids);
      Thumbnail thumbnail = thumbnailService.getAuctionThumbnail(auction.getId());
      if (thumbnail != null) auction.setThumbnail(thumbnail.getImage());
      });
    return finalResult;
  }

  public Auction insertAuction(Auction auction) {
    return auctionRepo.save(auction);
  }

  public void deleteAuction(Auction auction) {
    auctionRepo.delete(auction);
  }
}
