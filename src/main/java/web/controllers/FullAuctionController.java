package web.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import web.entities.Auction;
import web.entities.FullAuction;
import web.services.AuctionService;
import web.services.FullAuctionService;

import java.sql.Timestamp;
import java.time.LocalDateTime;

@RestController
@RequestMapping("/api/fullauctions")
public class FullAuctionController {

  @Autowired
  private FullAuctionService fullAuctionService;

  @GetMapping
  public Iterable<FullAuction> fullAuctions() {
    return fullAuctionService.getAllAuctions();
  }

}
