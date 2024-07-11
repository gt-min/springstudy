package com.min.boot.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

import com.min.boot.dto.BbsDTO;

@Mapper
public interface IBbsMapper {
  int insertBbsParent(BbsDTO bbsParent);
  int selectBbsCount();
  List<BbsDTO> selectBbsList(Map<String, Object> params);
}
