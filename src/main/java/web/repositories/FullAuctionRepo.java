package web.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import web.entities.Auction;

@Repository
public interface FullAuctionRepo extends JpaRepository<Auction, Long> {
}
