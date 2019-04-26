package web.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;
import web.configs.MyUserDetailsService;
import web.entities.User;
import web.services.UserService;

@RestController
public class UserController {

  @Autowired
  private UserService userService;
  @Autowired
  MyUserDetailsService myUserDetailsService;

  //  Authenticates if user is connected to server
  @GetMapping("/api/remember-me")
  public User rememberMe() {
    Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
    String username = authentication.getName();
    if (username.equals("anonymousUser")) {
      return null;
    }
    User user = userService.findByUsername(username);
    return user;
  }

  @PostMapping("/api/register")
  public String addNewUser(@RequestBody User user) {
    if (userService.findByUsername(user.getUsername()) != null) {
      return "username taken";
    }
    if (user.getUsername() != null && user.getEmail() != null && user.getPassword() != null) {
      myUserDetailsService.addUser(user.getId(), user.getUsername(), user.getPassword(), user.getEmail(), user.getName(), user.getSurname());
      return "success";
    }
    return null;
  }

  @PutMapping("/api/users/{id}")
  public void updateUser(
          @PathVariable Long id,
          @RequestBody User user) {
    userService.insertUser(user);
  }

  @DeleteMapping("/api/users/{id}")
  public void deleteUser(
          @PathVariable Long id,
          @RequestBody User user) {
    userService.deleteUser(user);
  }

}
