package com.min.app03.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.min.app03.service.BlogService;

@Controller
public class BlogController {

  private BlogService blogService;
  
  /* 생성자 주입 */
  
  @Autowired
  public BlogController(BlogService blogService) {
    super();
    this.blogService = blogService;
  }
  
  @RequestMapping("/blog/list")
  public String list(Model model) {
    model.addAttribute("blogList", blogService.getBlogList());
    return "blog/list";
  }
  
  @RequestMapping("/blog/detail")
  public String detail(@RequestParam("blogNo") int blogNo, Model model) {
    model.addAttribute("blog", blogService.getBlogByNo(blogNo));
    return "blog/detail";
  }
  
}
