package web.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import web.entities.Chat;
import web.entities.Message;
import web.entities.SocketEvent;
import web.repositories.ChatRepo;
import web.repositories.MessageRepo;

import java.util.ArrayList;
import java.util.List;

@Service
public class ChatService {

  @Autowired
  private ChatRepo chatRepo;
  @Autowired
  private MessageRepo messageRepo;
//  @Autowired
//  private SocketService socketService;

  // TODO: inner join chat and messages
//  public List<Chat> getAllChats(Long chatId) {
//
//    List<Message> Messages = messageRepo.findAllByChatId(chatId);
//    return new ArrayList<>();
//  }

  public List<Message> getAllMessages() {
    return messageRepo.findAll();
  }

  public Message insertMessage(Message Message) {
    Message MessageFromDB = messageRepo.save(Message);

//    emits the new Message to all connected users
//    to update auction
//    socketService.sendToAll(new SocketEvent("Message", MessageFromDB), SocketEvent.class);
    return MessageFromDB;
  }

  public void deleteMessage(Message Message) {
    messageRepo.delete(Message);
  }
}
