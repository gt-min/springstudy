package com.min.app03.dao;

import java.util.List;

import com.min.app03.dto.NewsDTO;

public interface NewsDAO {
  List<NewsDTO> getNewsList();
  NewsDTO getNewsByNo(int newsNo);
}