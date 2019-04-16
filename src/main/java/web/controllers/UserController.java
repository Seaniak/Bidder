package web.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import web.entities.User;
import web.services.UserService;

@RestController
@RequestMapping("/api/users")
public class UserController {

  @Autowired
  private UserService userService;

  @PostMapping()
  public User users(@RequestBody User user) {
    User u = userService.findByUsername(user.getUsername());
    if (u == null) {
      return null;
    } else if (u.getPassword().equals(user.getPassword())) {
      return u;
    }
    return null;
  }

/*  @PostMapping
  public String addNewUser(@RequestBody User user) {
    User u = userService.findByUsername(user.getUsername());
      if (u.getUsername() == null && u.getEmail() == null) {
        userService.insertUser(u);
        return u.getUsername() + " registered successfully.";
      } else {
        return "A user with that username or email already exists.";
      }
    }*/

  @PutMapping("/{id}")
  public void updateUser(
          @PathVariable Long id,
          @RequestBody User user) {
    userService.insertUser(user);
  }

  @DeleteMapping("/{id}")
  public void deleteUser(
          @PathVariable Long id,
          @RequestBody User user) {
    userService.deleteUser(user);
  }

}
