package com.min.boot.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.min.boot.service.IBbsService;

import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@RequestMapping("/bbs")
@Controller
public class BbsController {
  
  private final IBbsService bbsService;
  
  @GetMapping(value = "/list.do")
  public String listDo() {
    return "bbs/list";
  }
  
  @GetMapping(value = "/write.page")
  public String writePage() {
    return "bbs/write";
  }
  
  @PostMapping(value = "/saveParent.do")
  public String saveParentDo(HttpServletRequest request, RedirectAttributes rttr) {
    rttr.addFlashAttribute("saveParentMessage"
                         , bbsService.saveBbsParent(request) == 1 ? "원글 추가 성공" : "원글 추가 실패");
    return "redirect:/bbs/list.do";
  }
  
  
  
  

}
