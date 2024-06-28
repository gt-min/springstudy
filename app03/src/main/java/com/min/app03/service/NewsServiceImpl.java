package com.min.app03.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.min.app03.dao.NewsDAO;
import com.min.app03.dto.NewsDTO;
import com.min.app03.utils.PageUtils;

@Service
public class NewsServiceImpl implements NewsService {

  private NewsDAO newsDAO;
  private PageUtils pageUtils;
  
  /* 세터 주입 */
  
  @Autowired
  public void setBean(NewsDAO newsDAO, PageUtils pageUtils) {
    this.newsDAO = newsDAO;
    this.pageUtils = pageUtils;
  }
  
  @Override
  public List<NewsDTO> getNewsList() {
    return newsDAO.getNewsList();
  }

  @Override
  public NewsDTO getNewsByNo(int newsNo) {
    return newsDAO.getNewsByNo(newsNo);
  }

}
