package com.min.boot.mapper;

import org.apache.ibatis.annotations.Mapper;

import com.min.boot.dto.UserDTO;

@Mapper
public interface IUserMapper {
  int insertUser(UserDTO user);
}
