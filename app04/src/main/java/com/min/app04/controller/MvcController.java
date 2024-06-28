package com.min.app04.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class MvcController {
  
  @RequestMapping("/")
  public String main() {
    return "main";
  }

}
