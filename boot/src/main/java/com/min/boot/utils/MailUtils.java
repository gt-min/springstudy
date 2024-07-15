package com.min.boot.utils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;

import jakarta.mail.internet.InternetAddress;
import jakarta.mail.internet.MimeMessage;

@Component
public class MailUtils {
  
  @Autowired
  private JavaMailSender javaMailSender;
  
  public void sendMail(String to, String subject, String content) {
    
    try {

      MimeMessage mimeMessage = javaMailSender.createMimeMessage();
      MimeMessageHelper helper = new MimeMessageHelper(mimeMessage);
      helper.setFrom("gt_min@naver.com");
      helper.setTo(new InternetAddress(to));
      helper.setSubject(subject);
      mimeMessage.setContent(content, "text/html; charset=UTF-8");
      
      javaMailSender.send(mimeMessage);
      
    } catch (Exception e) {
      e.printStackTrace();
    }
    
  }
  
}