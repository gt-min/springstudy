package com.min.app03.dao;

import java.util.Arrays;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.min.app03.dto.NewsDTO;

@Repository
public class NewsDAOImpl implements NewsDAO {

  List<NewsDTO> newsList = Arrays.asList(
      new NewsDTO("뉴스제목1", "뉴스내용1")
    , new NewsDTO("뉴스제목2", "뉴스내용2")
    , new NewsDTO("뉴스제목3", "뉴스내용3")
      );
  
  @Override
  public List<NewsDTO> getNewsList() {
    return newsList;
  }

  @Override
  public NewsDTO getNewsByNo(int newsNo) {
    return newsList.get(newsNo - 1);
  }

}
