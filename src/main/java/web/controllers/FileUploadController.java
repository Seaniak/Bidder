package web.controllers;

import org.apache.tomcat.util.codec.binary.Base64;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import web.entities.Image;
import web.services.ImageService;

import javax.annotation.PostConstruct;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.UUID;

@RestController
@RequestMapping("/api/upload")
public class FileUploadController {
//  private static String currentWorkingDirectory = System.getProperty("user.dir");
//  private static String uploadDirectory = currentWorkingDirectory + "/src/main/resources/upload/";
//  private static String assetsDirectory = "src/main/resources/upload/";

//  @PostConstruct
//  public void createFolderIfMissing() {
//    File dirPath = new File(uploadDirectory);
//    if (!dirPath.exists()) {
//      dirPath.mkdirs();
//    }
//  }

  @Autowired
  private ImageService imageService;

//  Stores images in database encoded as Base64
  @PostMapping
  String uploadPicture(@RequestParam String file, @RequestParam long auctionId) {

    Image image = new Image(auctionId, false, file);
    imageService.insertImage(image);

    return "Image uploaded";
  }

//  @PostMapping
//  String uploadPicture(@RequestParam MultipartFile file, @RequestParam long auctionId) {
//
//    String encodedImage = null;
//    try {
//      encodedImage = new String(Base64.encodeBase64(file.getBytes()), "UTF-8");
////      encodedImage = Base64.getEncoder()
////              .encodeToString(file.getBytes());
//    } catch (IOException e) {
//      e.printStackTrace();
//    }
//    Image image = new Image(auctionId, false, encodedImage);
//
//    imageService.insertImage(image);
//
//    return "Image uploaded";
//  }

  //  @PostMapping
//  String uploadPicture(@RequestParam MultipartFile file) {
//    System.out.println(file.getContentType());
//
//    String filename = UUID.randomUUID().toString().
//            concat(file.getOriginalFilename().replaceAll("^.*(\\.[\\w]{3,4})$", "$1"));
//    File pathFile = new File(uploadDirectory + filename);
//    try {
//      file.transferTo(pathFile);
//    } catch (Exception e) {
//      return null;
//    }
//    return assetsDirectory + filename;
//  }

}
