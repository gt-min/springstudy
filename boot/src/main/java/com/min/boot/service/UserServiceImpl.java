package com.min.boot.service;

import org.springframework.stereotype.Service;

import com.min.boot.dto.UserDTO;
import com.min.boot.mapper.IUserMapper;
import com.min.boot.utils.SecurityUtils;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class UserServiceImpl implements IUserService {

  private final IUserMapper userMapper;
  private final SecurityUtils securityUtils;
  
  @Override
  public int signup(UserDTO user) {
    
    // 비밀번호 암호화
    user.setPw( securityUtils.getSha256(user.getPw()) );
    
    // 이름 크로스 사이트 스크립팅 처리
    user.setName( securityUtils.preventXss(user.getName()) );
    
    return userMapper.insertUser(user);
    
  }

}
