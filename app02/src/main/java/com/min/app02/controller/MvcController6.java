package com.min.app02.controller;

import java.io.PrintWriter;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Controller
public class MvcController6 {

  /*
   * RedirectAttributes
   * 
   * 1. redirect 할 때 전달할 데이터를 저장한다.
   * 2. flash attribute 형태로 저장해야 최종 이동 장소까지 전달된다.
   * 3. attribute 형태로 저장하면 Model 에 저장된다.
   */
  
  @RequestMapping("/blog/list")
  public String list() {
    
    return "blog/list";  /* forward 이동할 땐 JSP 경로 작성 : /WEB-INF/views/blog/list.jsp */
    
  }
  
  @RequestMapping("/blog/detail")
  public String detail(int blogNo, Model model) {
    
    model.addAttribute("blogNo", blogNo);  /* forward 이동할 땐 Model 에 저장 */
    
    return "blog/detail";  /* forward 이동할 땐 JSP 경로 작성 : /WEB-INF/views/blog/detail.jsp */
    
  }
  
  @RequestMapping("/blog/register")
  public String register(RedirectAttributes rttr) {
    
    int result = Math.random() < 0.7 ? 1 : 0;
    
    rttr.addFlashAttribute("insertResult", result > 0 ? "블로그 등록 성공" : "블로그 등록 실패");
    
    return "redirect:/blog/list";  /* redirect 이동할 땐 Mapping 작성 : @RequestMapping("/blog/list") */
    
  }
  
  @RequestMapping("/blog/modify")
  public String modify(@RequestParam int blogNo, RedirectAttributes rttr) {
    
    int result = Math.random() < 0.7 ? 1 : 0;
    
    rttr.addAttribute("blogNo", blogNo)  // Model 에 저장된 attribute 는 (실제로는 request 에 저장된 attribute) "{attribute}" 형식으로 확인 가능함
        .addFlashAttribute("updateResult", result > 0 ? "블로그 수정 성공" : "블로그 수정 실패");
    
    return "redirect:/blog/detail?blogNo={blogNo}";  /* redirect 이동할 땐 Mapping 작성 : @RequestMapping("/blog/detail") */
    
  }

  @RequestMapping("/blog/remove")
  public void remove(HttpServletRequest request, HttpServletResponse response) {
    
    // 응답을 직접 만드는 방식 (백영역에 프론트 코드가 포함되는 방식)
    
    try {
      
      response.setContentType("text/html; charset=UTF-8");
      PrintWriter out = response.getWriter();
      
      int result = Math.random() < 0.7 ? 1 : 0;
      
      out.println("<script>");
      if(result > 0) {
        out.println("alert('블로그 삭제 성공')");
        out.println("location.href='" + request.getContextPath() + "/blog/list'");
      } else {
        out.println("alert('블로그 삭제 실패')");
        out.println("history.back();");
      }
      out.println("</script>");
      out.close();
      
    } catch (Exception e) {
      e.printStackTrace();
    }
    
  }
  
}
