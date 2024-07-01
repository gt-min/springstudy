package com.min.app05;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;

import com.min.app05.dao.ContactDAO;

@SpringJUnitConfig(locations = {"file:src/main/webapp/WEB-INF/spring/root-context.xml", "file:src/main/webapp/WEB-INF/spring/appServlet/servlet-context.xml"})

/*
 * 
 */
// @ContextConfiguration(locations = {"file:src/main/webapp/WEB-INF/spring/appServlet/servlet-context.xml"})

class JUnitJupiterTestCase {

  @Autowired
  private ContactDAO contactDAO;

  @Test
  void test() {
    System.out.println(contactDAO.getContactByNo(0));
  }

}
