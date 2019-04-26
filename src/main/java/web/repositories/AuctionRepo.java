package web.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import web.entities.Auction;

import java.util.List;

@Repository
public interface AuctionRepo extends JpaRepository<Auction, Long> {
  Auction getAuctionById(Long id);
  List<Auction> findAllByUsername(String username);
  List<Auction> findAllByTitleContaining (String searchQuery);
  List<Auction> findAllByCategoryContaining (String searchQuery);
  List<Auction> findAllByDescriptionContaining (String searchQuery);
  List<Auction> findAllByAuctionConditionContaining (String searchQuery);
  List<Auction> findAllByUsernameContaining (String searchQuery);
}
