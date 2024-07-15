package com.min.boot.service;

import java.io.File;
import java.util.Map;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.min.boot.dto.BlogDTO;
import com.min.boot.dto.UserDTO;
import com.min.boot.mapper.IBlogMapper;
import com.min.boot.utils.FileUploadUtils;
import com.min.boot.utils.SecurityUtils;

import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class BlogServiceImpl implements IBlogService {

  private final IBlogMapper blogMapper;
  private final FileUploadUtils fileUploadUtils;
  private final SecurityUtils securityUtils;
  
  @Override
  public ResponseEntity<Map<String, Object>> summernoteImageUpload(MultipartFile multipartFile) {
    
    // 저장할 디렉터리 만들기
    String uploadPath = fileUploadUtils.getSummernotePath();
    File uploadDir = new File(uploadPath);
    if(!uploadDir.exists())
      uploadDir.mkdirs();
    
    // 저장할 파일명 만들기
    String filesystemName = fileUploadUtils.getFilesystemName(multipartFile.getOriginalFilename());
    
    // 저장
    File file = new File(uploadDir, filesystemName);
    try {
      multipartFile.transferTo(file);
    } catch (Exception e) {
      e.printStackTrace();
    }
    
    return ResponseEntity.ok(Map.of("url", uploadPath + "/" + filesystemName));
    
  }

  @Override
  public int saveBlog(BlogDTO blogDTO, HttpSession session) {
  
    blogDTO.setTitle(securityUtils.preventXss(blogDTO.getTitle()));
    
    UserDTO loginUser = (UserDTO) session.getAttribute("loginUser");
    blogDTO.setUserNo(loginUser.getUserNo());
    
    int insertResult = blogMapper.insertBlog(blogDTO);
    
    return insertResult;
    
  }
  
}
