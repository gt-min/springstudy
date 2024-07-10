package com.min.boot.service;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.ResponseEntity;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import com.min.boot.dto.UserDTO;
import com.min.boot.mapper.IUserMapper;
import com.min.boot.utils.SecurityUtils;

import jakarta.mail.internet.InternetAddress;
import jakarta.mail.internet.MimeMessage;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class UserServiceImpl implements IUserService {

  private final IUserMapper userMapper;
  private final SecurityUtils securityUtils;
  private final JavaMailSender javaMailSender;
  
  @Override
  public ResponseEntity<Map<String, Object>> sendCode(String email) {
    
    // 인증 코드 생성
    String code = securityUtils.getRandomCode(6, true, true);
    
    try {

      MimeMessage mimeMessage = javaMailSender.createMimeMessage();
      MimeMessageHelper helper = new MimeMessageHelper(mimeMessage);
      helper.setFrom("forspringlec@gmail.com");
      helper.setTo(new InternetAddress(email));
      helper.setSubject("[boot] 인증 요청");
      helper.setText("<div>인증코드는 <span>" + code + "</span>입니다.</div>", true);
      
//      mimeMessage.setFrom(new InternetAddress("forspringlec@gmail.com", "boot 관리자"));
//      mimeMessage.addRecipients(MimeMessage.RecipientType.TO, InternetAddress.parse(email));
//      mimeMessage.setSubject("[boot] 인증 요청");
//      mimeMessage.setContent("<div>인증코드는 <span>" + code + "</span>입니다.</div>", "text/html");
      
      javaMailSender.send(mimeMessage);
      
    } catch (Exception e) {
      e.printStackTrace();
    }
    
    // 메일 전송하기
    
    
//    .sendMail(
//          email
//        , "[boot] 인증 요청"
//        , "<div>인증코드는 <span>" + code + "</span>입니다.</div>");
    
    // {"code": "A43CF0"}
    return ResponseEntity.ok(Map.of("code", code));
    
  }
  
  @Override
  public int signup(UserDTO user) {
    
    // 비밀번호 암호화
    user.setPw( securityUtils.getSha256(user.getPw()) );
    
    // 이름 크로스 사이트 스크립팅 처리
    user.setName( securityUtils.preventXss(user.getName()) );
    
    return userMapper.insertUser(user);
    
  }
  
  @Override
  public void signin(HttpServletRequest request) {
    
    String email = request.getParameter("email");
    String pw = securityUtils.getSha256(request.getParameter("pw"));
    
    Map<String, Object> params = new HashMap<>();
    params.put("email", email);
    params.put("pw", pw);
    
    UserDTO loginUser = userMapper.getUserByMap(params);
    
    if(loginUser != null) {
      
      HttpSession session = request.getSession();
      session.setAttribute("loginUser", loginUser);  // session 유지 시간 : application.properties
      
      String ip = request.getRemoteAddr();
      String userAgent = request.getHeader("User-Agent");
      String sessionId = session.getId();
      
      params.put("ip", ip);
      params.put("userAgent", userAgent);
      params.put("sessionId", sessionId);
      
      userMapper.insertAccess(params);
      
    }
    
  }
  
  @Override
  public int leave(HttpSession session) {
    
    UserDTO loginUser = (UserDTO) session.getAttribute("loginUser");
    
    if(loginUser == null)  // 세션 만료 대비
      return 0;
    
    session.invalidate();
      
    return userMapper.deleteUser(loginUser.getUserNo());
    
  }
  
  
  
  
  
  
  
  
  

}
