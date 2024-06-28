package com.min.app03.service;

import java.util.List;

import com.min.app03.dto.NewsDTO;

public interface NewsService {
  List<NewsDTO> getNewsList();
  NewsDTO getNewsByNo(int newsNo);
}
