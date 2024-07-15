package com.min.boot.controller;

import java.util.Map;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

@RequestMapping("/blog")
@Controller
public class BlogController {

  @GetMapping(value = "/list.do")
  public String listDo() {
    return "blog/list";
  }
  
  @GetMapping(value = "/write.page")
  public String writePage() {
    return "blog/write";
  }
  
  @PostMapping(value = "/summernote/imageUpload.do", produces = "application/json")
  public ResponseEntity<Map<String, Object>> summernoteImageUploadDo(@RequestParam("file") MultipartFile multipartFile) {
    return ResponseEntity.ok(Map.of("url", "aaa"
                                  , "filename", multipartFile.getOriginalFilename()));    
  }
  
  
  
  
  
  
  
}
