package com.min.boot.utils;

import java.security.MessageDigest;

import org.springframework.stereotype.Component;

@Component
public class SecurityUtils {

  /* SHA-256
   * 1. 256비트(32바이트)로 암호화하는 해시 알고리즘이다.
   * 2. 암호화는 가능하고 복호화는 불가능하다. (단방향 알고리즘)  */
  
  public String getSha256(String source) {
    StringBuilder builder = new StringBuilder();
    try {
      MessageDigest md = MessageDigest.getInstance("SHA-256");
      md.update(source.getBytes());
      byte[] b = md.digest();
      for(int i = 0; i < b.length; i++) {
        builder.append(String.format("%02X", b[i]));
      }
    } catch (Exception e) {
      e.printStackTrace();
    }
    return builder.toString();
  }
  
  /* 크로스 사이트 스크립팅 (Cross Site Scripting)
   * 1. 입력 란에 스크립트 코드를 넣는 공격 방식이다.
   * 2. 스크립트 코드에 반드시 필요한 <script> 태그의 무력화를 위해서
   *    "<" 기호는 "&lt;"으로 바꾸고 ">" 기호는 "&gt;"으로 바꾼다.  */
  
  public String preventXss(String source) {
    return source.replace("<", "&lt;").replace(">", "&gt;");
  }
  
  
  
  
  
  
  
  
}
