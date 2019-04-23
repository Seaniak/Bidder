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

  public List<String> getAuctionImageData(Long auctionId) {
    List<Image> images = imageRepo.findAllByAuctionId(auctionId);
    List<String> imagePaths = new ArrayList<>();

    for (Image image : images) {
      imagePaths.add(image.getImageData());
    }
    return imagePaths;
  }

  public List<String> getAllImagesAsBase64(Long auctionId) {
    List<Image> images = getAuctionImages(auctionId);
    List<String> base64images = new ArrayList<>();
    for (Image image : images) {
      base64images.add(image.getImageData());
    }
    return base64images;
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
