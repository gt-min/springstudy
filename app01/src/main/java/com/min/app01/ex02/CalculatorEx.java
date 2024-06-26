package com.min.app01.ex02;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.AbstractApplicationContext;

public class CalculatorEx {

  public static void main(String[] args) {
  
    // Spring Container 에 있는 Bean 처리       : ApplicationContext
    // close() 처리가 가능한 ApplicationContext : AbstractApplicationContext
    // @Bean 으로 만든 Bean 처리                : AnnotationConfigApplicationContext
    
    AbstractApplicationContext ctx = new AnnotationConfigApplicationContext(AppConfig.class);
    
    Calculator calculator1 = ctx.getBean("calculator1", Calculator.class);
    Calculator calculator2 = (Calculator)ctx.getBean("calculator2");

    System.out.println(calculator1.getMaker() + ", " + calculator1.getPrice() + ", " + calculator1.getBuied());
    System.out.println(calculator2.getMaker() + ", " + calculator2.getPrice() + ", " + calculator2.getBuied());
    
    calculator1.getAdder().add(10, 20);
    calculator1.getSubtractor().subtract(30, 40);
    
    calculator2.getAdder().add(10, 20);
    calculator2.getSubtractor().subtract(30, 40);
    
    ctx.close();
    
  }

}
