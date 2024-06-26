package com.min.app02.controller;

import java.util.Optional;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.min.app02.vo.PageVO;

import jakarta.servlet.http.HttpServletRequest;

@Controller
public class MvcController3 {

  /*
   * Query String 방식의 요청 파라미터 처리하기
   * 1. HttpServletRequest
   * 2. @RequestParam
   * 3. 커맨드 객체 (요청 파라미터를 필드로 가지고 있는 클래스 타입의 객체)
   */
  
  @RequestMapping("/param1")
  public String param1(HttpServletRequest request) {
    
    // 요청 파라미터 page (디폴트 1)
    Optional<String> optPage = Optional.ofNullable(request.getParameter("page"));
    int page = Integer.parseInt(optPage.orElse("1"));
    System.out.println(page);
    
    // 요청 파라미터 sort (디폴트 DESC)
    Optional<String> optSort = Optional.ofNullable(request.getParameter("sort"));
    String sort = optSort.orElse("DESC");
    System.out.println(sort);
    
    return "index";
    
  }
  
  
  /*
   * @RequestParam
   * 
   * 1. 요청 파라미터를 받는 역할을 수행한다.
   * 2. 형식
   *   @RequestParam(value = "파라미터이름", required = 필수여부, defaultValue = "기본값")
   * 3. 요청 파라미터가 정상적으로 전달된다면 @RequestParam 을 생략할 수도 있다.
   */
  @RequestMapping("/param2")
  public String param2(@RequestParam(value = "page", required = false, defaultValue = "1") int page          // 요청 파라미터 page (디폴트 1)
                     , @RequestParam(value = "sort", required = false, defaultValue = "DESC") String sort) { // 요청 파라미터 sort (디폴트 DESC)
    
    System.out.println(page);
    System.out.println(sort);
    
    return "index";
    
  }
  
  /*
   * 커맨드 객체
   * 
   * 1. 요청 파라미터를 필드로 가지고 있는 클래스 타입의 객체이다.
   * 2. 파라미터를 setter 와 연결해서 값을 받는다.
   * 3. 자동으로 Model 에 저장된다.
   */
  
  @RequestMapping("/param3")
  public String param3(PageVO page) {
    
    System.out.println(page.getPage());
    System.out.println(page.getSort());
    
    return "index";
    
  }
  
}
