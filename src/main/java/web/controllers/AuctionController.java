package web.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import web.entities.Auction;
import web.entities.Bid;
import web.entities.Thumbnail;
import web.services.*;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/api/auctions")
public class AuctionController {

  @Autowired
  private AuctionService auctionService;
  @Autowired
  private ImageService imageService;
  @Autowired
  private BidService bidService;
  @Autowired
  private ThumbnailService thumbnailService;

  @GetMapping
  public Iterable<Auction> auctions() {
    return auctionService.getAllAuctions();
  }

  @GetMapping("/user={username}")
  public List<Auction> getUserAuctions(@PathVariable String username) {
    return auctionService.getUserAuctions(username);
  }

  @GetMapping("/{id}")
  public Auction getOneAuction(@PathVariable Long id) {
    Auction auction = auctionService.getAuctionById(id);
    if (auction == null) return null;

    List<Bid> bids = bidService.getAuctionBids(auction.getId());
    auction.setBids(bids);

    List<String> images = imageService.getAuctionImageData(id);
    auction.setImages(images);

    return auction;
  }

  @PostMapping
    public Auction publishAuction(@RequestBody Auction auction) {

//    Timestamp is received as string from frontend, and parsed here
    auction.setCreateTime(Timestamp.valueOf(LocalDateTime.now()));
    auction.setEndTime(Timestamp.valueOf(auction.getFrontEndEndTime()));

    Auction auctionFromDb = auctionService.insertAuction(auction);

    Thumbnail thumbnail = new Thumbnail(auctionFromDb.getId(), auction.getThumbnail());
    thumbnailService.insertThumbnail(thumbnail);

    return auctionFromDb;
  }

  @PostMapping("/search/{query}")
  public Iterable<Auction> getSearchResult (@PathVariable(required = false) String query) {
    if (query.equals("-default-"))
      query = "";
    return auctionService.getSearchResult(query);
  }

  @PutMapping("/{id}")
  public void updateAuction(
          @PathVariable Long id,
          @RequestBody Auction auction) {
    auctionService.insertAuction(auction);
  }

  @DeleteMapping("/{id}")
  public void deleteAuction(
          @PathVariable Long id,
          @RequestBody Auction auction) {
    auctionService.deleteAuction(auction);
  }

}
