package web.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import web.entities.Message;

import java.util.List;

@Repository
public interface MessageRepo extends JpaRepository<Message, Long> {
  List<Message> findAllBySenderAndRecipient(String sender, String recipient);

  @Query("SELECT DISTINCT m.sender, m.recipient, m.text FROM Message AS m WHERE m.sender = ?1 OR m.recipient = ?1 ORDER BY m.timestamp DESC")
  List<Object[]> getChatList(String username);
}
