package com.min.app01.ex03;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

// @ComponentScan : @Component 가 저장된 패키지를 등록
@ComponentScan(basePackages = {"com.min.app01.ex03"})

// @Configuration : Bean 을 만드는 클래스 (Bean 설정에 관련된 클래스)
@Configuration
public class AppConfig {

}
