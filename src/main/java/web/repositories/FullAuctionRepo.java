package web.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import web.entities.FullAuction;
import web.entities.Image;

import java.util.List;

@Repository
public interface FullAuctionRepo extends JpaRepository<FullAuction, Long> {

  public FullAuction findByAuctionId(long id);
}
