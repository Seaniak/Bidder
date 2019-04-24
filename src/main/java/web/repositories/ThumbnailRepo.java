package web.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import web.entities.Thumbnail;

import java.util.List;

@Repository
public interface ThumbnailRepo extends JpaRepository<Thumbnail, Long> {
  Thumbnail findByAuctionId(Long auctionId);

}
