package web.controllers;

import org.springframework.beans.factory.annotation.Autowired;
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

  @PostMapping("/api/register")
  public Object addNewUser(@RequestBody User user) {
    if(userService.findByUsername(user.getUsername()) != null){
      return new Object() {
        public String message = "A user with that username or email already exists.";
      };
    }

    if (user.getUsername() != null && user.getEmail() != null) {
      myUserDetailsService.addUser(user.getUsername(), user.getPassword(), user.getEmail());
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
