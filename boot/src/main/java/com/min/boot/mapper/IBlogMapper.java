package com.min.boot.mapper;

import org.apache.ibatis.annotations.Mapper;

import com.min.boot.dto.BlogDTO;
import com.min.boot.dto.ImageDTO;

@Mapper
public interface IBlogMapper {
  int insertBlog(BlogDTO blogDTO);
  int insertSummernoteImage(ImageDTO imageDTO);
}
