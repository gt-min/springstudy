package com.min.boot.service;

import org.springframework.stereotype.Service;

import com.min.boot.dto.BbsDTO;
import com.min.boot.dto.UserDTO;
import com.min.boot.mapper.IBbsMapper;
import com.min.boot.utils.PageUtils;
import com.min.boot.utils.SecurityUtils;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class BbsServiceImpl implements IBbsService {

  private final IBbsMapper bbsMapper;
  private final PageUtils pageUtils;
  private final SecurityUtils securityUtils;
  
  @Override
  public int saveBbsParent(HttpServletRequest request) {
        
    HttpSession session = request.getSession();
    UserDTO loginUser = (UserDTO)session.getAttribute("loginUser");
    
    if(loginUser == null)  // 로그인이 풀린 유저
      return 0;
    
    String contents = securityUtils.preventXss(request.getParameter("contents"));
    int userNo = loginUser.getUserNo();
    BbsDTO bbsParent = BbsDTO.builder()
        .contents(contents)
        .userNo(userNo)
        .build();
    
    return bbsMapper.insertBbsParent(bbsParent);
    
  }
  
  
  
  
  
  
  
  
  
  
  
}
