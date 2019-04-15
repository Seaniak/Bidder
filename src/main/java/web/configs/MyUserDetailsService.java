package web.configs;

import org.springframework.context.annotation.Configuration;
import web.entities.User;
import web.repositories.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import web.services.UserService;

import javax.annotation.PostConstruct;

@Configuration
public class MyUserDetailsService implements UserDetailsService {

  private BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();

  public BCryptPasswordEncoder getEncoder() {
    return encoder;
  }

  @Autowired
  private UserService userService;

  @PostConstruct
  private void createDefaultUsers() {
    if (userService.findByUsername("user") == null) {
      addUser("user", "password");
    }
  }

  @Override
  public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
    User user = userService.findByUsername(username);
    if (user == null) {
      throw new UsernameNotFoundException("User not found by name: " + username);
    }
    return toUserDetails(user);
  }

  public void addUser(String name, String password) {
    User u = new User(name, encoder.encode(password));
    try {
      userService.insertUser(u);
    } catch (Exception ex) {
      ex.printStackTrace();
    }
  }

  private UserDetails toUserDetails(User user) {
    return org.springframework.security.core.userdetails.User
            .withUsername(user.getUsername())
            .password(user.getPassword())
            .roles("USER").build();
  }
}
