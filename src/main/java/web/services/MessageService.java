package web.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import web.entities.Message;
import web.repositories.MessageRepo;

import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class MessageService {
  DateTimeFormatter timestampFormat = DateTimeFormatter.ofPattern("dd' 'MMM HH:mm");

  @Autowired
  private MessageRepo messageRepo;

  public List<Message> getAllMessages() {
    return messageRepo.findAll();
  }

  public List<Message> getMessagesFromSenderOrRecipient(String currentUser, String recipient) {
    List<Message> messages = messageRepo.findAllBySenderAndRecipient(currentUser, recipient);
    List<Message> recipients = messageRepo.findAllBySenderAndRecipient(recipient, currentUser);
    messages.addAll(recipients);

    for (Message msg : messages) {
      msg.setTime(msg.getTimestamp().toLocalDateTime().format(timestampFormat));
    }
    messages.sort((a, b) -> a.getTimestamp().compareTo(b.getTimestamp()));
    return messages;
  }

  public List<Map<String, String>> getOngoingChats(String username) {
    List<Object[]> messages = messageRepo.getChatList(username);
    List<Map<String, String>> chats = new ArrayList<>();

    for (Object[] message : messages) {
      Map<String, String> chatObject = new HashMap();
      if (!message[0].equals(username)) {
        if (checkDuplicateUser(chats, message[0])) continue;

        chatObject.putIfAbsent("username", message[0].toString());
        chatObject.putIfAbsent("text", message[2].toString());

        chats.add(chatObject);
      } else if (!message[1].equals(username)) {
        if (checkDuplicateUser(chats, message[1])) continue;

        chatObject.putIfAbsent("username", message[1].toString());
        chatObject.putIfAbsent("text", message[2].toString());

        chats.add(chatObject);
      }
    }
    return chats;
  }

  private boolean checkDuplicateUser(List<Map<String, String>> chats, Object obj){
    return chats.stream()
            .anyMatch(msg -> msg.get("username").equals(obj.toString()));
  }

  public Message insertMessage(Message message) {
    Message MessageFromDB = messageRepo.save(message);
    MessageFromDB.setTime(MessageFromDB.getTimestamp().toLocalDateTime().format(timestampFormat));
    return MessageFromDB;
  }

  public void deleteMessage(Message message) {
    messageRepo.delete(message);
  }
}
