package com.min.app03.dao;

import java.util.List;

import com.min.app03.dto.BbsDTO;

public interface BbsDAO {
  List<BbsDTO> getBbsList();
  BbsDTO getBbsByNo(int bbsNo);
}