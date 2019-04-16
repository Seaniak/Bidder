package web.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.PostConstruct;
import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.nio.charset.StandardCharsets;
import java.util.Base64;
import java.util.List;

@RestController
public class FileUploader {
  private static String currentWorkingDirectory = System.getProperty("user.dir");
  private static String uploadDirectory = currentWorkingDirectory + "/src/main/resources/static/uploads/";

  @PostConstruct
  public void createFolderIfMissing() {
    File dirPath = new File(uploadDirectory);
    if (!dirPath.exists()) {
      dirPath.mkdirs();
    }
  }

  public static String handleFileUpload(MultipartFile file) {
    String filePath = uploadDirectory + file.getOriginalFilename();
    File targetLocation = new File(filePath);
    try {
      file.transferTo(targetLocation);
    } catch (Exception ex) {
      ex.printStackTrace();
    }

    return filePath;
  }

  public static boolean handleFileUpload(String fileName, String file) {

    byte[] imageData = Base64.getDecoder().decode(file.getBytes(StandardCharsets.UTF_8));

    String filePath = uploadDirectory + fileName;
    try (OutputStream out = new FileOutputStream(new File(filePath))) {

      out.write(imageData);
      return true;

    } catch (Exception ex) {
      ex.printStackTrace();
    }
    return false;
  }
}
