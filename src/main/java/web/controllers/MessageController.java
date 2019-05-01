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

import java.util.List;

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
}
