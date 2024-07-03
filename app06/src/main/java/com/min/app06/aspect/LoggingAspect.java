package com.min.app06.aspect;

import java.util.Arrays;
import java.util.Map;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Aspect
@Component
public class LoggingAspect {

  /* PointCut 표현식 (어떤 클래스의 어떤 메소드에서 적용할 것인가?)
   *     execution(반환타입 클래스.메소드(매개변수))                  */
  @Pointcut(value = "execution(* com.min.app06.controller.*Controller.wp_*(..))")
  public void setPointcut() {
    
  }
  
  /* Advice
   * 
   *             | @Before   | @After    | @Around
   * ------------|-----------|-----------|--------------------
   * 1. 반환타입 | void      | void      | Object
   * 2. 매개변수 | JoinPoint | JoinPoint | ProceedingJoinPoint  */
  
  @Before(value = "setPointcut()")
  public void logging(JoinPoint joinPoint) {
    
    /* 요청 Log 남기기 : 요청 방식 / 요청 주소 / 요청 파라미터 */
    
    HttpServletRequest request = 
        ((ServletRequestAttributes)RequestContextHolder.getRequestAttributes()).getRequest();
    
    String requestMethod = request.getMethod();
    String requestURI = request.getRequestURI();
    
    String params = "";
    for(Map.Entry<String, String[]> entry : request.getParameterMap().entrySet()) {
      params += entry.getKey() + ":" + Arrays.toString(entry.getValue()) + " ";
    }
    
    log.debug("{} | {}", requestMethod, requestURI);
    log.debug("{}", params);
    
  }
  
}
