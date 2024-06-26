package com.min.app01.ex01;

import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.GenericXmlApplicationContext;

public class CalculatorEx {

  public static void main(String[] args) {
  
    // Spring Container 에 있는 Bean 처리       : ApplicationContext
    // close() 처리가 가능한 ApplicationContext : AbstractApplicationContext
    // XML 파일로 만든 Bean 처리                : GenericXmlApplicationContext
    
    AbstractApplicationContext ctx = new GenericXmlApplicationContext("com/min/app01/ex01/app-context.xml");
    
    Adder adder = ctx.getBean("adder", Adder.class);
    Subtractor subtractor = (Subtractor)ctx.getBean("subtractor");
    Calculator calculator1 = ctx.getBean("calculator1", Calculator.class);
    Calculator calculator2 = (Calculator)ctx.getBean("calculator2");

    adder.add(10, 15);
    subtractor.subtract(10, 15);
    System.out.println(calculator1.getMaker() + ", " + calculator1.getPrice() + ", " + calculator1.getBuied());
    System.out.println(calculator2.getMaker() + ", " + calculator2.getPrice() + ", " + calculator2.getBuied());
    
    ctx.close();
    
  }

}
