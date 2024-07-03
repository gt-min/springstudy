package com.min.app06.config;

import java.util.Arrays;

import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.aop.Advisor;
import org.springframework.aop.aspectj.AspectJExpressionPointcut;
import org.springframework.aop.support.DefaultPointcutAdvisor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.TransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.transaction.interceptor.MatchAlwaysTransactionAttributeSource;
import org.springframework.transaction.interceptor.RollbackRuleAttribute;
import org.springframework.transaction.interceptor.RuleBasedTransactionAttribute;
import org.springframework.transaction.interceptor.TransactionInterceptor;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

@Configuration
@ComponentScan(basePackages = {
    "com.min.app06"
})
@MapperScan(basePackages = {
    "com.min.app06.mapper"
})
@EnableAspectJAutoProxy
@EnableTransactionManagement
public class AppConfig {

  /* HikariConfig → HikariDataSource ┌→ SqlSessionFactory → SqlSessionTemplate 
   *                                 │
   *                                 └→ TransactionManager → TransactionInterceptor → Advisor  
   *                                                              (Advice)                      */
  
  @Bean
  public HikariConfig hikariConfig() {
    
    HikariConfig config = new HikariConfig();
    
    /* Using OracleDriver
     * config.setDriverClassName("oracle.jdbc.OracleDriver");
     * config.setJdbcUrl("jdbc:oracle:thin:@localhost:1521:xe");  */
    
    /* Using Log4jdbc-log4j2 */
    config.setDriverClassName("net.sf.log4jdbc.sql.jdbcapi.DriverSpy");
    config.setJdbcUrl("jdbc:log4jdbc:oracle:thin:@localhost:1521:xe");
    
    config.setUsername("GREEN");
    config.setPassword("GREEN");
    
    return config;
    
  }
  
  @Bean
  public HikariDataSource hikariDataSource() {
    return new HikariDataSource(hikariConfig());
  }
  
  @Bean
  public SqlSessionFactory sqlSessionFactory() throws Exception {
    SqlSessionFactoryBean factoryBean = new SqlSessionFactoryBean();
    factoryBean.setDataSource(hikariDataSource());
    factoryBean.setConfigLocation(new PathMatchingResourcePatternResolver().getResource("classpath:com/min/app06/mybatis/config/mybatis-config.xml"));
    factoryBean.setMapperLocations(new PathMatchingResourcePatternResolver().getResources("classpath:com/min/app06/mybatis/mapper/*.xml"));
    return factoryBean.getObject();
  }
  
  @Bean
  public SqlSessionTemplate sqlSessionTemplate() throws Exception {
    return new SqlSessionTemplate(sqlSessionFactory());
  }
  
  @Bean
  public TransactionManager transactionManager() {
    return new DataSourceTransactionManager(hikariDataSource());
  }
  
  @Bean
  public TransactionInterceptor transactionInterceptor() {
    
    // RollBack Rule
    RuleBasedTransactionAttribute attribute = new RuleBasedTransactionAttribute();
    attribute.setRollbackRules(Arrays.asList(new RollbackRuleAttribute(Exception.class)));
    
    // TransactionAttributeSource
    MatchAlwaysTransactionAttributeSource attributeSource = new MatchAlwaysTransactionAttributeSource();
    attributeSource.setTransactionAttribute(attribute);
    
    return new TransactionInterceptor(transactionManager(), attributeSource);
    
  }
  
  @Bean
  public Advisor advisor() {
    
    // Pointcut
    AspectJExpressionPointcut pointcut = new AspectJExpressionPointcut();
    pointcut.setExpression("execution(* com.min.app06.service.*Impl.tx_*(..))");
    
    return new DefaultPointcutAdvisor(pointcut, transactionInterceptor());
    
  }
  
}