package com.min.app06;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;

import com.min.app06.config.AppConfig;
import com.min.app06.service.ContactService;

@SpringJUnitConfig(classes = {AppConfig.class})

class TxTestCase {

  @Autowired
  private ContactService contactService;
  
  @Test
  void test() {
    contactService.tx_txTest();
  }

}
