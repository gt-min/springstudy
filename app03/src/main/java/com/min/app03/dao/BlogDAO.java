package com.min.app03.dao;

import java.util.List;

import com.min.app03.dto.BlogDTO;

public interface BlogDAO {
  List<BlogDTO> getBlogList();
  BlogDTO getBlogByNo(int blogNo);
}
