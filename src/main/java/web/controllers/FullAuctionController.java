package web.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import web.entities.Bid;
import web.entities.FullAuction;
import web.entities.Image;
import web.services.FullAuctionService;
import web.services.ImageService;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/fullauctions")
public class FullAuctionController {

  @Autowired
  private FullAuctionService fullAuctionService;
  private ImageService imageService;

  @GetMapping
  public Iterable<FullAuction> fullAuctions() {
    return fullAuctionService.getAllAuctions();
  }

  // TODO: Create new FullAuction() when fetching?
  @GetMapping("/{id}")
  public FullAuction getOneFullAuction(@PathVariable Long id) {
//    get images and bids from database with same auction ID
    List<Image> images = imageService.getAuctionImages(id);
    List<Bid> bids = new ArrayList<>();
    
    FullAuction fullAuction = new FullAuction(images, bids);

    return fullAuction;
  }

}
