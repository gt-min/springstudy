package com.min.app02.controller;

import java.util.Optional;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("/app")
@Controller
public class MvcController4 {

  /* Path Variable : 경로에 포함된 변수 */
  
  @RequestMapping("/users/{id}")
  public String users(@PathVariable(value = "id") int userId) {
    
    System.out.println(userId);
    
    return "index";
    
  }
  
  @RequestMapping(value = {"/members", "/members/{id}"})
  public String members(@PathVariable(value = "id", required = false) Optional<String> opt) {
    
    int memberId = Integer.parseInt(opt.orElse("1"));
    
    System.out.println(memberId);
    
    return "index";
    
  }
  
  @RequestMapping("/page/{page}/sort/{sort}")
  public String page(@PathVariable(value = "page") int page
                   , @PathVariable(value = "sort") String sort) {
    
    System.out.println(page);
    System.out.println(sort);
    
    return "index";
    
  }
  
}
