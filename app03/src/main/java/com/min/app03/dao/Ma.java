package com.min.app03.dao;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.AbstractApplicationContext;

public class Ma {
  public static void main(String[] args) {
    AbstractApplicationContext ctx = new AnnotationConfigApplicationContext("com.min.app03.dao");
    int size = ctx.getBean("bbsDAOImpl", BbsDAO.class).getBbsList().size();
    System.out.println(size);
    ctx.close();
  }
}