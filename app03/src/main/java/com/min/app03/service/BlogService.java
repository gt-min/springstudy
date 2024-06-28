package com.min.app03.service;

import java.util.List;

import com.min.app03.dto.BlogDTO;

public interface BlogService {
  List<BlogDTO> getBlogList();
  BlogDTO getBlogByNo(int blogNo);
}
