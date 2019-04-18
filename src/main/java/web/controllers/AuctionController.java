package web.controllers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import web.entities.Auction;
import web.services.AuctionService;
import java.sql.Timestamp;
import java.time.LocalDateTime;

@RestController
@RequestMapping("/api/auctions")
public class AuctionController {
  private static Logger LOG = LoggerFactory.getLogger(AuctionController.class);


  @Autowired
  private AuctionService auctionService;

  @GetMapping
  public Iterable<Auction> auctions() {
    return auctionService.getAllAuctions();
  }

  @PostMapping
  public Auction publishAuction(@RequestBody Auction auction) {
    if (auction.getCreateTime() == null)
      auction.setCreateTime(Timestamp.valueOf(LocalDateTime.now()));

    auction.getFiles().forEach(f -> LOG.info(f.toString()));

//    FileUploader.handleFileUpload(post.getImageName(), post.getImageData());

    return auctionService.insertAuction(auction);
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
