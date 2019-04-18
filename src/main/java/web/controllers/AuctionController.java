package web.controllers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import web.entities.Auction;
import web.entities.Image;
import web.services.AuctionService;
import web.services.ImageService;

import java.sql.Timestamp;
import java.time.LocalDateTime;

@RestController
@RequestMapping("/api/auctions")
public class AuctionController {

  @Autowired
  private AuctionService auctionService;

  @Autowired
  private ImageService imageService;

  @GetMapping
  public Iterable<Auction> auctions() {
    return auctionService.getAllAuctions();
  }

  @PostMapping
  public Auction publishAuction(@RequestBody Auction auction) {
    if (auction.getCreateTime() == null)
      auction.setCreateTime(Timestamp.valueOf(LocalDateTime.now()));

    Auction auctionFromDb = auctionService.insertAuction(auction);

    for (String image : auction.getImagePaths()) {
      Image img = new Image(auctionFromDb.getId(), image, false);
      imageService.insertImage(img);
    }

    return auctionFromDb;
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
