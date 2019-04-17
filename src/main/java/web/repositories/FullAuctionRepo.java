package web.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import web.entities.FullAuction;

@Repository
public interface FullAuctionRepo extends JpaRepository<FullAuction, Long> {
}
