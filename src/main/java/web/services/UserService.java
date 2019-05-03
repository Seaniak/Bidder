package web.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import web.entities.User;
import web.repositories.UserRepo;

@Service
public class UserService {

  @Autowired
  private UserRepo userRepo;

  public User findByUsername(String username) {
    User user = userRepo.findByUsername(username);
    return user;
  }

  public User insertUser(User user) {
    return userRepo.save(user);
  }

  public void deleteUser(User user) {
    userRepo.delete(user);
  }
}
