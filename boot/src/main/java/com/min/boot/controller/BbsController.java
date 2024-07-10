package com.min.boot.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.min.boot.service.IBbsService;

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
  
  
  
  
  

}
