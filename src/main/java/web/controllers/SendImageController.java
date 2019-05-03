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
    return imageService.getAllImagesAsBase64(auctionId);
  }
}
