package com.min.boot.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.min.boot.dto.UserDTO;
import com.min.boot.service.IUserService;

import lombok.RequiredArgsConstructor;

/* 실무에서 많이 사용하는 DI 패턴
 *   @RequiredArgsConstructor
 *   private final IUserService userService;  */

@RequiredArgsConstructor
@RequestMapping("/user")
@Controller
public class UserController {

  private final IUserService userService;
  
  @GetMapping(value = "/signup.page")
  public String signupPage() {
    return "user/signup";
  }
  
  @PostMapping(value = "/signup.do")
  public String signupDo(UserDTO user, RedirectAttributes rttr) {
    String redirectURL;
    String message;
    if(userService.signup(user) == 1) {
      redirectURL = "/main.do";
      message = "회원 가입 성공";
    } else {
      redirectURL = "/user/signup.page";
      message = "회원 가입 실패";
    }
    rttr.addFlashAttribute("signupMessage", message);
    return "redirect:" + redirectURL;
  }
  
  
  
  
  
  
  
}
