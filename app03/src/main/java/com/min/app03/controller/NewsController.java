package com.min.app03.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.min.app03.service.NewsService;

@Controller
public class NewsController {

  private NewsService newsService;
  
  /* 세터 주입 */
  
  @Autowired
  public void setNewsService(NewsService newsService) {
    this.newsService = newsService;
  }

  @RequestMapping("/news/list")
  public String list(Model model) {
    model.addAttribute("newsList", newsService.getNewsList());
    return "news/list";
  }
  
  @RequestMapping("/news/detail")
  public String detail(@RequestParam("newsNo") int newsNo, Model model) {
    model.addAttribute("news", newsService.getNewsByNo(newsNo));
    return "news/detail";
  }
  
}
