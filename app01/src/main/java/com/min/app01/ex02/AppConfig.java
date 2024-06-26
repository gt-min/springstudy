package com.min.app01.ex02;

import java.time.Instant;
import java.util.Date;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

// @Configuration : Bean 을 만드는 클래스 (Bean 설정에 관련된 클래스)

@Configuration
public class AppConfig {
  
  /* 메소드 == Bean */
  
  /*
   * 반환타입 : Bean 의 타입 ( <bean class=""> )
   * 메소드명 : Bean 의 이름 ( <bean id="">    )
   */
  
  @Bean
  Adder adder() {
    return new Adder();
  }
  
  @Bean
  Subtractor subtractor() {
    return new Subtractor();
  }
  
  @Bean
  Date today() {
    return Date.from(Instant.now());
  }
  
  @Bean
  Calculator calculator1() {
    
    Calculator calculator = new Calculator();
    calculator.setMaker("samsung");          // <property name="maker"      value="samsung" />
    calculator.setPrice(100);                // <property name="price"      value="100" />
    calculator.setAdder(adder());            // <property name="adder"      ref="adder" />
    calculator.setSubtractor(subtractor());  // <property name="subtractor" ref="subtractor" />
    calculator.setBuied(today());            // <property name="buied"      ref="today" />
    
    return calculator;
    
  }
  
  @Bean
  Calculator calculator2() {
    
    return new Calculator("LG"          // <constructor-arg value="LG" />
                        , 200           // <constructor-arg value="200" />
                        , adder()       // <constructor-arg ref="adder" />
                        , subtractor()  // <constructor-arg ref="subtractor" />
                        , today());     // <constructor-arg ref="today" />
    
  }

}
