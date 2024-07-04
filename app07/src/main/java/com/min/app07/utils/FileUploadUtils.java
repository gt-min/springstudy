package com.min.app07.utils;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.UUID;

import org.springframework.stereotype.Component;

@Component
public class FileUploadUtils {

  private static final LocalDate TODAY = LocalDate.now();
  
  public String getUploadPath() {
    return "/upload/" + DateTimeFormatter.ofPattern("yyyy/MM/dd").format(TODAY);
  }
  
  public String getFilesystemName(String originalFilename) {
    String extension;
    if(originalFilename.endsWith(".tar.gz")) {
      extension = ".tar.gz";
    }
    else {
      extension = originalFilename.substring(originalFilename.lastIndexOf("."));
    }
    return UUID.randomUUID().toString().replace("-", "") + extension;
  }
  
}
