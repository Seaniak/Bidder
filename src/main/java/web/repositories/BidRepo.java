package web.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import web.entities.Bid;
import java.util.List;

@Repository
public interface BidRepo extends JpaRepository<Bid, Long> {

  public List<Bid> findAllByAuctionId(Long auctionId);


}
