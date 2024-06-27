package com.min.app03.dao;

import java.util.Arrays;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.min.app03.dto.BbsDTO;

/*
@Component : Spring Container 에 생성된 Bean 의 타입 (BbsDAO, BbsDAOImpl)
             Spring Container 에 생성된 Bean 의 이름 (bbsDAOImpl)
*/

@Repository  /* DAO 의 @Component */
public class BbsDAOImpl implements BbsDAO {

  List<BbsDTO> bbsList = Arrays.asList(
      new BbsDTO("제목1", "내용1")
    , new BbsDTO("제목2", "내용2")
    , new BbsDTO("제목3", "내용3")
      );
  
  @Override
  public List<BbsDTO> getBbsList() {
    return bbsList;
  }

  @Override
  public BbsDTO getBbsByNo(int bbsNo) {
    return bbsList.get(bbsNo - 1);
  }

}