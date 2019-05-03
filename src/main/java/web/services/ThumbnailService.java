package web.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import web.entities.Thumbnail;
import web.repositories.ThumbnailRepo;

@Service
public class ThumbnailService {

  @Autowired
  private ThumbnailRepo thumbnailRepo;

  public Thumbnail getAuctionThumbnail(Long auctionId) {
    return thumbnailRepo.findByAuctionId(auctionId);
  }

  public void insertThumbnail(Thumbnail thumbnail) {
    thumbnailRepo.save(thumbnail);
  }

  public void deleteThumbnail(Thumbnail thumbnail) {
    thumbnailRepo.delete(thumbnail);
  }
}
