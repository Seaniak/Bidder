package web.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.web.bind.annotation.*;
import web.entities.Auction;
import web.entities.Image;
import web.services.AuctionService;
import web.services.ImageService;

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

  @GetMapping
  public Iterable<Auction> auctions() {
    return auctionService.getAllAuctions();
  }

  @GetMapping("/{id}")
  public Auction getOneAuction(@PathVariable Long id) {
    Auction auction = auctionService.getAuctionById(id);

    List<Image> images = imageService.getAuctionImages(id);

    List<String> imageFiles = new ArrayList<>();

    for (Image image : images) {

      File img = null;
      try {
        img = new ClassPathResource(image.getPath()).getFile();

        String encodedImage = Base64.getEncoder()
                .withoutPadding()
                .encodeToString(Files.readAllBytes(img.toPath()));

        imageFiles.add(encodedImage);
      } catch (IOException e) {
        e.printStackTrace();
      }
    }

    auction.setImages(imageFiles);
    auction.setBids(new ArrayList<>());

    return auction;
  }

  @PostMapping
  public Auction publishAuction(@RequestBody Auction auction) {
    if (auction.getCreateTime() == null)
      auction.setCreateTime(Timestamp.valueOf(LocalDateTime.now()));
/*      auction.setCreateTime(Timestamp.valueOf(String.valueOf(auction.getCreateTime())));
      auction.setEndTime(Timestamp.valueOf(String.valueOf(auction.getEndTime())));*/
/*      auction.setEndTime(Timestamp.valueOf(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(auction.getEndTime())));*/


    Auction auctionFromDb = auctionService.insertAuction(auction);

    for (String image : auction.getImagePaths()) {
      Image img = new Image(auctionFromDb, false, image);
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
