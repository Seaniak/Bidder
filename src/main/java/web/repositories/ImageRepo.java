package web.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import web.entities.Image;

import java.util.List;

@Repository
public interface ImageRepo extends JpaRepository<Image, Long> {

  public List<Image> findAllByAuctionId(Long auctionId);


}
