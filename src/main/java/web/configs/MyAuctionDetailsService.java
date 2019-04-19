/*
package web.configs;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.multipart.MultipartFile;
import web.entities.Auction;
import web.services.AuctionService;

import java.sql.Timestamp;
import java.util.List;

@Configuration
public class MyAuctionDetailsService {

   @Autowired
   private AuctionService auctionService;

   public void addAuction(long userId, String title, String description, Timestamp createTime, Timestamp endTime,
                          int startSum, int reservedSum, String category, String auctionCondition) {
      Auction auction = new Auction(userId, title, description, createTime, endTime, startSum, reservedSum, category,
              auctionCondition);
      try {
         auctionService.insertAuction(auction);
      } catch (Exception e) {
         e.printStackTrace();
      }
   }

}
*/
