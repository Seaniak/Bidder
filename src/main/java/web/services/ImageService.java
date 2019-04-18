package web.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import web.entities.Auction;
import web.entities.Image;
import web.repositories.AuctionRepo;
import web.repositories.ImageRepo;

import java.util.List;

@Service
public class ImageService {

  @Autowired
  private ImageRepo imageRepo;

  public List<Image> getAllImages(){
    return imageRepo.findAll();
  }

  public Image insertImage(Image image){
    return imageRepo.save(image);
  }

  public void deleteImage(Image image){
    imageRepo.delete(image);
  }
}
