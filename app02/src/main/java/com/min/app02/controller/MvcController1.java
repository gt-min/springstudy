package com.min.app02.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

// @Controller : 컨트롤러로 등록하기

@Controller
public class MvcController1 {

  /* 하나의 요청 및 응답 == 메소드 */
  
  /*
   * 1. 반환타입
   *   1) String       : 응답 JSP 의 경로와 이름을 반환한다.
   *   2) void         : 요청 주소를 응답 JSP 의 경로와 이름으로 인식한다.
   *   3) ModelAndView : 응답 JSP 의 경로와 이름과 JSP 로 보낼 데이터를 저장하는 객체이다.
   * 
   * 2. 메소드명
   *   의미 없다.
   *   
   * 3. 매개변수
   *   1) 요청과 응답을 처리하기 위한 각종 클래스가 사용된다.
   *   2) 주요 매개변수
   *     (1) HttpServletRequest
   *     (2) HttpServletResponse
   *     (3) Model
   *     (4) HttpSession
   *     (5) RedirectAttributes
   */
  
  
  // welcome file
  
  /*
   * @RequestMapping
   * 1. 요청 주소와 요청 메소드를 작성할 수 있다.
   * 2. 요청 메소드를 생략하면 GET 방식이 사용된다.
   * 3. 요청 주소는 value 를 이용한다.
   *   1) value = "/"      :  /contextPath      요청을 의미한다.
   *   2) value = "/list"  :  /contextPath/list 요청을 의미한다.
   *   3) value = {"/list", "/list.do"} : 2개 이상의 요청은 배열로 처리한다.
   * 4. 요청 메소드는 method 를 이용한다.
   *   1) method = RequestMethod.GET
   *   2) method = RequestMethod.POST
   */
  
  @RequestMapping(value = {"/", "/main"}, method = RequestMethod.GET)
  public String welcome() {
    
    return "index";
    
    /*
     * return "index"; 해석하기
     * 
     * 1. servlet-context.xml (DispatcherServlet) 에 정의된 ViewResolver 에 정보가 전달된다.
     * 2. prefix="/WEB-INF/views/" 값을 리턴 앞에 붙인다.  (/WEB-INF/views/index)
     * 3. suffix=".jsp"            값을 리턴 뒤에 붙인다.  (/WEB-INF/views/index.jsp)
     */
    
  }
  
}
