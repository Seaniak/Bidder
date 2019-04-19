package web.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import web.entities.Auction;
import web.entities.Image;

import java.util.List;

@Repository
public interface ImageRepo extends JpaRepository<Image, Long> {

  public List<Image> getAuctionImages(Long auctionId);

}
