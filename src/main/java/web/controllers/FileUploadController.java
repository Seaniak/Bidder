package web.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import web.entities.Image;
import web.services.ImageService;

@RestController
@RequestMapping("/api/upload")
public class FileUploadController {

  @Autowired
  private ImageService imageService;

//  Stores images in database encoded as Base64
  @PostMapping
  String uploadPicture(@RequestParam String file, @RequestParam long auctionId) {

    Image image = new Image(auctionId, false, file);
    imageService.insertImage(image);

    return "Image uploaded";
  }

}
