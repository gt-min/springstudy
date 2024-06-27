package com.min.app03.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.min.app03.service.BbsService;

@Controller
public class BbsController {

  /* 컨트롤러는 서비스 객체를 필드로 선언하고 사용한다. */
  
  @Autowired
  private BbsService bbsService;
  
  @RequestMapping("/bbs/list")
  public String list(Model model) {
    model.addAttribute("bbsList", bbsService.getBbsList());
    return "bbs/list";
  }
  
  @RequestMapping("/bbs/detail")
  public String detail(@RequestParam int bbsNo, Model model) {
    model.addAttribute("bbs", bbsService.getBbsByNo(bbsNo));
    return "bbs/detail";
  }
  
}