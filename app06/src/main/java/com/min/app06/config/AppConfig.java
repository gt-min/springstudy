package com.min.app06.config;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@MapperScan(basePackages = {
    "com.min.app06.mapper"
})
public class AppConfig {

}
