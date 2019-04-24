package web.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.web.bind.annotation.*;
import web.entities.Auction;
import web.entities.Bid;
import web.entities.Image;
import web.entities.Thumbnail;
import web.services.AuctionService;
import web.services.BidService;
import web.services.ImageService;
import web.services.ThumbnailService;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Base64;
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

  private void getImagesAsBase64(List<Image> images) {
    List<String> imageFiles = new ArrayList<>();

    if (images.size() > 0) {
      for (Image image : images) {
        File fileExists = new File(image.getImageData());

        if (!fileExists.exists()) continue;

        File img = null;
        try {

          img = new ClassPathResource(image.getImageData()).getFile();

          String encodedImage = Base64.getEncoder()
                  .withoutPadding()
                  .encodeToString(Files.readAllBytes(img.toPath()));

          imageFiles.add(encodedImage);
        } catch (IOException e) {
          e.printStackTrace();
        }
      }
    }
  }

  @PostMapping
    public Auction publishAuction(@RequestBody Auction auction) {
    if (auction.getCreateTime() == null)
      //auction.setCreateTime(Timestamp.valueOf(LocalDateTime.now()));
      System.out.println("auction time is: " + auction.getCreateTime());
      System.out.println(auction.toString());
      System.out.println("auction title is: " + auction.getTitle());
/*      auction.setCreateTime(Timestamp.valueOf(String.valueOf(auction.getCreateTime())));
      auction.setEndTime(Timestamp.valueOf(String.valueOf(auction.getEndTime())));*/
    /*      auction.setEndTime(Timestamp.valueOf(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(auction.getEndTime())));*/


    Auction auctionFromDb = auctionService.insertAuction(auction);
    Thumbnail thumbnail = new Thumbnail(auctionFromDb.getId(), auction.getThumbnail());

    thumbnailService.insertThumbnail(thumbnail);

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
