package com.min.app02.controller;

import java.util.Arrays;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.min.app02.vo.NewsVO;

@Controller
public class MvcController5 {

  /*
   * Model
   * 
   * 1. JSP 로 forward 할 때 전달할 데이터를 저장한다.
   * 2. 내부적으로는 HttpServletRequest 를 사용해서 데이터를 저장한다.
   */
  
  List<NewsVO> newsList = Arrays.asList(
      new NewsVO("오늘은 목요일", 10)
      , new NewsVO("오늘 무더위", 20)
      );
  
  @RequestMapping(value = "/news/list")
  public String list(Model model) {
    
    model.addAttribute("newsList", newsList);
    
    return "news/list";  /* /WEB-INF/views/news/list.jsp */
    
  }
  
  
  @RequestMapping("/news/detail")
  public ModelAndView detail(@RequestParam("newsNo") int newsNo) {
    
    ModelAndView mav = new ModelAndView();
    mav.addObject("news", newsList.get(newsNo - 1));  // model.addAttribute() 와 동일한 동작
    mav.setViewName("news/detail");  /* /WEB-INF/views/news/detail.jsp  */
    
    return mav;
    
  }
  
}
