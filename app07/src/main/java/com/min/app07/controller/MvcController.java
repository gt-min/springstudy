package com.min.app07.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MvcController {

  @GetMapping(value = "/")
  public String main() {
    return "main";
  }
  
}
