package web.controllers;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.PostConstruct;
import java.io.File;
import java.util.UUID;

@RestController
@RequestMapping("/api/upload")
public class FileUploadController {
  private static String currentWorkingDirectory = System.getProperty("user.dir");
  private static String uploadDirectory = currentWorkingDirectory + "/src/main/resources/static/upload/";
  private static String assetsDirectory = "static/upload/";

  @PostConstruct
  public void createFolderIfMissing() {
    File dirPath = new File(uploadDirectory);
    if (!dirPath.exists()) {
      dirPath.mkdirs();
    }
  }

  @PostMapping
  String uploadPicture(@RequestParam MultipartFile file) {
    System.out.println(file.getContentType());

    String filename = UUID.randomUUID().toString().
            concat(file.getOriginalFilename().replaceAll("^.*(\\.[\\w]{3,4})$", "$1"));
    File pathFile = new File(uploadDirectory + filename);
    try {
      file.transferTo(pathFile);
    } catch (Exception e) {
      return null;
    }
    return assetsDirectory + filename;
  }
}
