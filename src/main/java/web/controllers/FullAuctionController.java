package web.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import web.entities.Auction;
import web.services.AuctionService;
import web.services.FullAuctionService;

import java.sql.Timestamp;
import java.time.LocalDateTime;

@RestController
@RequestMapping("/auctions")
public class FullAuctionController {

  @Autowired
  private FullAuctionService fullAuctionService;

  @GetMapping("/{id}")
  public Auction auction(@PathVariable Long id) {
    return fullAuctionService.getAuction(id);
  }
}
