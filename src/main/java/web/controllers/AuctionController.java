package web.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import web.entities.Auction;
import web.services.AuctionService;
import java.sql.Timestamp;
import java.time.LocalDateTime;

@RestController
@RequestMapping("/api/auctions")
public class AuctionController {

  @Autowired
  private AuctionService auctionService;

  @GetMapping
  public Iterable<Auction> auctions() {
    return auctionService.getAllAuctions();
  }

  @GetMapping("/{id}")
  public Auction auction(@PathVariable Long id) {
    return auctionService.getAuction(id);
  }


  @PostMapping
  public Auction publishAuction(@RequestBody Auction auction) {
    if (auction.getCreateTime() == null)
      auction.setCreateTime(Timestamp.valueOf(LocalDateTime.now()));

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
