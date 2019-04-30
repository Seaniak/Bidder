package web.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import web.entities.Message;
import web.repositories.MessageRepo;

import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

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

  public List<String> getOngoingChats(String username) {
    List<Object[]> messages = messageRepo.getChatList(username);
    List<String> chats = new ArrayList<>();

    for (Object[] message: messages) {
      if(!message[0].equals(username) && !chats.contains(message[0].toString()))
        chats.add(message[0].toString());
      else if (!message[1].equals(username) &&!chats.contains(message[1].toString()))
        chats.add(message[1].toString());
    }
    return chats;
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
