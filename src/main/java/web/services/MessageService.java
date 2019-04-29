package web.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import web.entities.Message;
import web.entities.SocketEvent;
import web.repositories.MessageRepo;

import java.util.ArrayList;
import java.util.List;

@Service
public class MessageService {

  @Autowired
  private MessageRepo messageRepo;
//  @Autowired
//  private SocketService socketService;

//  public List<Message> getChatMessages(Long chatId) {
//    List<Message> Messages = messageRepo.findAllByChatId(chatId);
//    return Messages == null ? new ArrayList<>() : Messages;
//  }

  public List<Message> getAllMessages() {
    return messageRepo.findAll();
  }

  public Message insertMessage(Message message) {
    Message MessageFromDB = messageRepo.save(message);

//    emits the new Message to all connected users
//    to update auction
//    socketService.sendToAll(new SocketEvent("Message", MessageFromDB), SocketEvent.class);
    return MessageFromDB;
  }

  public void deleteMessage(Message message) {
    messageRepo.delete(message);
  }
}
