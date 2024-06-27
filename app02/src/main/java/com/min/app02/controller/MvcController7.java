package com.min.app02.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.min.app02.vo.UserVO;

import jakarta.servlet.http.HttpSession;

@Controller
public class MvcController7 {

  @RequestMapping(value = "/user/login", method = RequestMethod.POST)
  public String login(HttpSession session, UserVO user, @RequestParam String redirectURL) {
    
    // 세션에 로그인 사용자 정보 저장하기
    session.setAttribute("loginUser", user);  // JSP 에서 확인하는 방법 : ${sessionScope.loginUser} 
    
    // 세션 유지 시간 설정하기
    session.setMaxInactiveInterval(60 * 10);  // 10분
    
    // redirect
    return "redirect:" + redirectURL;
    
  }
  
  @RequestMapping("/user/logout")
  public String logout(HttpSession session) {
    
    // 세션 초기화
    session.invalidate();
    
    // redirect
    return "redirect:/main";
    
  }
  
  @RequestMapping("/user/mypage")
  public String mypage(HttpSession session, Model model) {
    
    UserVO loginUser = (UserVO) session.getAttribute("loginUser");
    
    model.addAttribute("loginUser", loginUser);
    
    return "user/mypage";
    
  }
  
}
