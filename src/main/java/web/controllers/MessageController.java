package web.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import web.entities.Message;
import web.services.MessageService;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/messages")
public class MessageController {

  @Autowired
  private MessageService messageService;

  @GetMapping("/user={currentUser}&recipient={recipient}")
  public List<Message> getAuctionChat(@PathVariable String currentUser, @PathVariable String recipient) {
    Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
    // returns null if user is not logged in
    if (!currentUser.equals(authentication.getName())) return null;

    return messageService.getMessagesFromSenderOrRecipient(currentUser, recipient);
  }

  @GetMapping("/get-ongoing-chats&user={username}")
  public List<Map<String, String>> getOngoingChats(@PathVariable String username) {
    Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
    // returns empty list if user is not logged in
    if (!username.equals(authentication.getName())) return new ArrayList<>();

    return messageService.getOngoingChats(username);
  }
}
