package com.min.app07.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.min.app07.service.IUploadService;

import jakarta.servlet.http.HttpServletRequest;

@Controller
public class UploadController {

  private IUploadService uploadService;
  
  @Autowired  
  public UploadController(IUploadService uploadService) {
    super();
    this.uploadService = uploadService;
  }

  @GetMapping(value = "/write1.do")
  public String write1() {
    return "write1";
  }

  @GetMapping(value = "/write2.do")
  public String write2() {
    return "write2";
  }
  
  @PostMapping(value = "/register1.do")
  public String register1(HttpServletRequest request, RedirectAttributes rttr) {
    rttr.addFlashAttribute("isSuccess", uploadService.registerUpload1(request) == 1);
    return "redirect:/list.do";
  }
  
  @PostMapping(value = "/register2.do", produces = "application/json")
  public ResponseEntity<Map<String, Object>> register2(MultipartHttpServletRequest multipartRequest) {
    // {"isSuccess": true}
    return ResponseEntity.ok(Map.of("isSuccess", uploadService.registerUpload2(multipartRequest) == 1));
  }
  
  @GetMapping(value = "/list.do")
  public String list(Model model) {
    model.addAttribute("uploadList", uploadService.getUploadList());
    return "list";
  }

  @GetMapping(value = "/detail.do")
  public String detail(@RequestParam int uploadNo, Model model) {
    uploadService.loadUpload(uploadNo, model);
    return "detail";
  }
  
  @GetMapping(value = "/download.do", produces = "application/octet-stream")
  public ResponseEntity<Resource> download(@RequestHeader(name = "User-Agent") String userAgent
                                         , @RequestParam(name = "fileNo") int fileNo) {
    return uploadService.download(userAgent, fileNo);
  }
  
  @GetMapping(value = "/downloadAll.do", produces = "application/octet-stream")
  public ResponseEntity<Resource> downloadAll(@RequestParam(name = "uploadNo") int uploadNo) {
    return uploadService.downloadAll(uploadNo);
  }
  
}
