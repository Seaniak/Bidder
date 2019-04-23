package web.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import web.services.ImageService;

import java.util.List;

@RestController
@RequestMapping("/api/get_image")
public class SendImageController {

  @Autowired
  private ImageService imageService;

  @GetMapping("/{auctionId}")
  public List<String> getImages(@PathVariable Long auctionId) {
    List<String> images = imageService.getAllImagesAsBase64(auctionId);

    return images;

//    byte[] imageData = null;
//    try {
//      imageData = Files.readAllBytes(image.toPath());
//    } catch (IOException e) {
//      e.printStackTrace();
//    }
//
//    return ResponseEntity.ok()
//            .contentType(MediaType.IMAGE_PNG)
//            .header(HttpHeaders.CONTENT_TYPE, "image/png")
//            .body(imageData);
  }
}
