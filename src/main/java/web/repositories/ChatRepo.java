package web.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import web.entities.Chat;
import web.entities.Message;

import java.util.List;

@Repository
public interface ChatRepo extends JpaRepository<Chat, Long> {
//  List<Chat> findAllBy
  Chat findByAuctionId(Long auctionId);
}
