package com.min.app03.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.min.app03.dao.BbsDAO;
import com.min.app03.dto.BbsDTO;
import com.min.app03.utils.PageUtils;

/*
@Component : Spring Container 에 생성되는 Bean 의 타입 (BbsServiceImpl, BbsService)
             Spring Container 에 생성되는 Bean 의 이름 (bbsServiceImpl)
*/

@Service  /* 서비스의 @Component */
public class BbsServiceImpl implements BbsService {

  /* 서비스는 DAO 객체를 필드로 선언하고 사용한다. */

  @Autowired  /* Spring Container 에 저장된 Bean 중에서 BbsDAO 타입의 Bean 을 가져오시오. */
  private BbsDAO bbsDAO;
  
  @SuppressWarnings("unused")
  @Autowired
  private PageUtils pageUtils;
  
  @Override
  public List<BbsDTO> getBbsList() {
    return bbsDAO.getBbsList();
  }

  @Override
  public BbsDTO getBbsByNo(int bbsNo) {
    return bbsDAO.getBbsByNo(bbsNo);
  }

}
