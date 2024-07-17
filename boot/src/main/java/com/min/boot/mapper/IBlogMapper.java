package com.min.boot.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

import com.min.boot.dto.BlogCommentDTO;
import com.min.boot.dto.BlogDTO;
import com.min.boot.dto.ImageDTO;

@Mapper
public interface IBlogMapper {
  int insertBlog(BlogDTO blogDTO);
  int insertSummernoteImage(ImageDTO imageDTO);
  int getBlogCount();
  List<BlogDTO> getBlogList(Map<String, Object> params);
  int updateHit(int blogNo);
  BlogDTO getBlogByNo(int blogNo);
  int insertBlogCommentParent(BlogCommentDTO blogCommentParentDTO);
  int getBlogCommentCount(int blogNo);
  List<BlogCommentDTO> getBlogCommentList(Map<String, Object> params);
  int updateGroupOrder(BlogCommentDTO blogCommentParentDTO);
  int insertBlogCommentChild(BlogCommentDTO blogCommentChildDTO);
}
