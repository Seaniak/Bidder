package web.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import web.configs.MyUserDetailsService;
import web.entities.User;
import web.services.UserService;

import javax.servlet.http.HttpServletRequest;
import java.security.Principal;

@RestController
public class UserController {

  @Autowired
  private UserService userService;

  @Autowired
  MyUserDetailsService myUserDetailsService;

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
  public Object addNewUser(@RequestBody User user) {
    if (userService.findByUsername(user.getUsername()) != null) {
      return new Object() {
        public String message = "A user with that username or email already exists.";
      };
    }

    if (user.getUsername() != null && user.getEmail() != null) {
      myUserDetailsService.addUser(user.getId(), user.getUsername(), user.getPassword(), user.getEmail(), user.getName(), user.getSurname());
      return new Object() {
        public String message = user.getUsername() + " registered successfully.";
      };
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
