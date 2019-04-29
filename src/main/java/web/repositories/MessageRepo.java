package web.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import web.entities.Chat;
import web.entities.Message;

import java.util.List;

@Repository
public interface MessageRepo extends JpaRepository<Message, Long> {
  List<Chat> findAllByChatId(long id);
  List<Chat> findAllBySender(String sender);
  List<Chat> findAllByRecipient(String recipient);
}
