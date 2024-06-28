package com.min.app03.dao;

import java.util.Arrays;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.min.app03.dto.BlogDTO;

@Repository
public class BlogDAOImpl implements BlogDAO {

  List<BlogDTO> blogList = Arrays.asList(
      new BlogDTO("블로그제목1", "블로그내용1")
    , new BlogDTO("블로그제목2", "블로그내용2")
    , new BlogDTO("블로그제목3", "블로그내용3")
      );
  
  @Override
  public List<BlogDTO> getBlogList() {
    return blogList;
  }

  @Override
  public BlogDTO getBlogByNo(int blogNo) {
    return blogList.get(blogNo - 1);
  }

}
