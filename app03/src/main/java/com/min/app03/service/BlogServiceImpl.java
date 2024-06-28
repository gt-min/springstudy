package com.min.app03.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.min.app03.dao.BlogDAO;
import com.min.app03.dto.BlogDTO;
import com.min.app03.utils.PageUtils;

@Service
public class BlogServiceImpl implements BlogService {

  private BlogDAO blogDAO;
  private PageUtils pageUtils;
  
  /* 생성자 주입 */
  
  @Autowired
  public BlogServiceImpl(BlogDAO blogDAO, PageUtils pageUtils) {
    super();
    this.blogDAO = blogDAO;
    this.pageUtils = pageUtils;
  }
  
  @Override
  public List<BlogDTO> getBlogList() {
    return blogDAO.getBlogList();
  }

  @Override
  public BlogDTO getBlogByNo(int blogNo) {
    return blogDAO.getBlogByNo(blogNo);
  }

}
