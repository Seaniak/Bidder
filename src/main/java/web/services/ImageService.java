package web.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import web.entities.Image;
import web.repositories.ImageRepo;

import java.util.ArrayList;
import java.util.List;

@Service
public class ImageService {

  @Autowired
  private ImageRepo imageRepo;

  public List<Image> getAuctionImages(Long auctionId) {
    return imageRepo.findAllByAuctionId(auctionId);
  }

  public List<String> getAuctionImagePaths(Long auctionId) {
    List<Image> images = imageRepo.findAllByAuctionId(auctionId);
    List<String> imagePaths = new ArrayList<>();

    for (Image image : images) {
      imagePaths.add(image.getPath());
    }
    return imagePaths;
  }

  public List<Image> getAllImages() {
    return imageRepo.findAll();
  }

  public Image insertImage(Image image) {
    return imageRepo.save(image);
  }

  public void deleteImage(Image image) {
    imageRepo.delete(image);
  }
}
