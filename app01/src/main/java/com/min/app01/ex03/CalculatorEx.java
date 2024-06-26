package com.min.app01.ex03;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.AbstractApplicationContext;

public class CalculatorEx {

  public static void main(String[] args) {
  
    // Spring Container 에 있는 Bean 처리       : ApplicationContext
    // close() 처리가 가능한 ApplicationContext : AbstractApplicationContext
    // @Component 로 만든 Bean 처리             : AnnotationConfigApplicationContext
    
    AbstractApplicationContext ctx = new AnnotationConfigApplicationContext(AppConfig.class);
    
    Adder adder = ctx.getBean("adder", Adder.class);
    Subtractor subtractor = ctx.getBean("subtractor", Subtractor.class);
    
    adder.add(10, 20);
    subtractor.subtract(30, 40);
    
    ctx.close();
    
  }

}
