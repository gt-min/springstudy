package com.min.boot.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("/user")
@Controller
public class UserController {

  @GetMapping(value = "/signup.page")
  public String signupPage() {
    return "user/signup";
  }
  
}
