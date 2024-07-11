package com.min.boot.mapper;

import org.apache.ibatis.annotations.Mapper;

import com.min.boot.dto.BbsDTO;

@Mapper
public interface IBbsMapper {
  int insertBbsParent(BbsDTO bbsParent);
}
