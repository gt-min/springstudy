package com.min.app07.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
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

  @PostMapping(value = "/register.do")
  public String register(HttpServletRequest request, RedirectAttributes rttr) {
    rttr.addFlashAttribute("isSuccess", uploadService.registerUpload(request) == 1);
    return "redirect:/list.do";
  }
  
  @GetMapping(value = "/list.do")
  public String list(Model model) {
    model.addAttribute("uploadList", uploadService.getUploadList());
    return "main";
  }

  @GetMapping(value = "/detail.do")
  public String detail(@RequestParam int uploadNo, Model model) {
    uploadService.loadUpload(uploadNo, model);
    return "detail";
  }
  
}
