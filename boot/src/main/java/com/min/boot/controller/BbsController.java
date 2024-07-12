package com.min.boot.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
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
  public String listDo(HttpServletRequest request, Model model) {
    bbsService.loadBbsList(request, model);
    return "bbs/list";
  }
  
  /*  /bbs/write.page 요청 이전에 로그인 여부를 점검하는 SigninCheck 인터셉터가 동작한다.  */
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

  @PostMapping(value = "/saveChild.do")
  public String saveChildDo(HttpServletRequest request, RedirectAttributes rttr) {
    rttr.addFlashAttribute("saveChildMessage"
        , bbsService.saveBbsChild(request) == 1 ? "답글 추가 성공" : "답글 추가 실패");
    return "redirect:/bbs/list.do";
  }
  
  @GetMapping(value = "/remove.do")
  public String removeDo(@RequestParam int bbsNo, RedirectAttributes rttr) {
    rttr.addFlashAttribute("removeMessage"
        , bbsService.removeBbs(bbsNo) == 1 ? "삭제 성공" : "삭제 실패");
    return "redirect:/bbs/list.do";
  }

}
