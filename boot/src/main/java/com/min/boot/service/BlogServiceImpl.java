package com.min.boot.service;

import java.io.File;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import com.min.boot.dto.BlogDTO;
import com.min.boot.dto.ImageDTO;
import com.min.boot.dto.UserDTO;
import com.min.boot.mapper.IBlogMapper;
import com.min.boot.utils.FileUploadUtils;
import com.min.boot.utils.PageUtils;
import com.min.boot.utils.SecurityUtils;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;

@Transactional
@RequiredArgsConstructor
@Service
public class BlogServiceImpl implements IBlogService {

  private final IBlogMapper blogMapper;
  private final FileUploadUtils fileUploadUtils;
  private final SecurityUtils securityUtils;
  private final PageUtils pageUtils;
  
  @Transactional(readOnly = true)
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
    
    // blogDTO.contents 에 포함된 <img src="/summernote/..."> 태그 Parsing 해서 image_t 에 넣기
    Document document = Jsoup.parse(blogDTO.getContents());
    Elements elements = document.getElementsByTag("img");
    if(elements != null) {
      for(Element element : elements) {
        String src = element.attr("src");
        int lashSlash = src.lastIndexOf("/");
        String uploadPath = src.substring(0, lashSlash);
        String filesystemName = src.substring(lashSlash + 1);
        ImageDTO imageDTO = ImageDTO.builder()
            .blogNo(blogDTO.getBlogNo())
            .uploadPath(uploadPath)
            .filesystemName(filesystemName)
            .build();
        blogMapper.insertSummernoteImage(imageDTO);
      }
    }
    
    return insertResult;
    
  }
  
  @Transactional(readOnly = true)
  @Override
  public ResponseEntity<Map<String, Object>> getBlogList(HttpServletRequest request) {
    
    int page = Integer.parseInt(request.getParameter("page"));
    int display = 20;
    int total = blogMapper.getBlogCount();
    
    pageUtils.setPaging(total, display, page);
    
    Map<String, Object> params = new HashMap<>();
    params.put("begin", pageUtils.getBegin());
    params.put("end", pageUtils.getEnd());
    
    List<BlogDTO> blogList = blogMapper.getBlogList(params);
    
    return null;
    
  }
  
  
  
  
  
  
  
  
  
  
  
  
  
  
}
