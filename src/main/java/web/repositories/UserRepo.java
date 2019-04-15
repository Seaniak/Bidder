package web.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import web.entities.User;

@Repository
public interface UserRepo extends JpaRepository<User, Long> {
  public User findByUsername(String username);
}
