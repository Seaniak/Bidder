package web.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import web.entities.Auction;
import web.entities.Image;

@Repository
public interface ImageRepo extends JpaRepository<Image, Long> {
}
