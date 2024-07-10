package com.min.boot.mapper;

import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

import com.min.boot.dto.UserDTO;

@Mapper
public interface IUserMapper {
  int insertUser(UserDTO user);
  UserDTO getUserByMap(Map<String, Object> params);
  int insertAccess(Map<String, Object> params);
  int deleteUser(int userNo);
}
