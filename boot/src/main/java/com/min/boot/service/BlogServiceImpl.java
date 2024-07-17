package com.min.boot.service;

import java.io.File;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import com.min.boot.dto.BlogCommentDTO;
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
    String paging = pageUtils.getAsyncPaging();
    
    return ResponseEntity.ok(Map.of("blogList", blogList, "paging", paging));
    
  }
  
  @Override
  public int increseHit(int blogNo) {
    return blogMapper.updateHit(blogNo);
  }
  
  @Transactional(readOnly = true)
  @Override
  public BlogDTO getBlogByNo(int blogNo) {
    return blogMapper.getBlogByNo(blogNo);
  }
  
  @Override
  public ResponseEntity<Map<String, Object>> saveBlogCommentParent(HttpServletRequest request) {
    
    // 작성자
    UserDTO loginUser = (UserDTO) request.getSession().getAttribute("loginUser");
    
    if(loginUser == null)  // 로그인이 풀린 유저
      return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);  // 401
    
    // 원글 정보
    String contents = securityUtils.preventXss(request.getParameter("contents"));
    
    // 블로그 정보
    int blogNo = Integer.parseInt(request.getParameter("blogNo"));
    
    BlogCommentDTO blogCommentParentDTO = BlogCommentDTO.builder()
        .userNo(loginUser.getUserNo())
        .blogNo(blogNo)
        .contents(contents).build();
    
    int insertResult = blogMapper.insertBlogCommentParent(blogCommentParentDTO);
    
    return ResponseEntity.ok(Map.of("isSuccess", insertResult == 1));
    
  }
  
  @Transactional(readOnly = true)
  @Override
  public ResponseEntity<Map<String, Object>> getBlogCommentList(HttpServletRequest request) {
    
    // 블로그 정보
    int blogNo = Integer.parseInt(request.getParameter("blogNo"));
    
    // 페이징 처리 정보
    int page = Integer.parseInt(request.getParameter("page"));
    int display = 3;
    int total = blogMapper.getBlogCommentCount(blogNo);
    
    pageUtils.setPaging(total, display, page);
    
    Map<String, Object> params = new HashMap<>();
    params.put("begin", pageUtils.getBegin());
    params.put("end", pageUtils.getEnd());
    params.put("blogNo", blogNo);
    
    List<BlogCommentDTO> blogCommentList = blogMapper.getBlogCommentList(params);
    String paging = pageUtils.getAsyncPaging();
    
    return ResponseEntity.ok(Map.of("blogCommentList", blogCommentList
                                  , "paging", paging
                                  , "blogCommentCount", total));
    
  }
  
  @Override
  public ResponseEntity<Map<String, Object>> saveBlogCommentChild(HttpServletRequest request) {
    
    // 작성자
    UserDTO loginUser = (UserDTO) request.getSession().getAttribute("loginUser");
    
    if(loginUser == null)  // 로그인이 풀린 유저
      return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);  // 401

    // 원글 정보
    int depth = Integer.parseInt(request.getParameter("depth"));
    int groupNo = Integer.parseInt(request.getParameter("groupNo"));
    int groupOrder = Integer.parseInt(request.getParameter("groupOrder"));
    
    BlogCommentDTO blogCommentParentDTO = BlogCommentDTO.builder()
        .depth(depth)
        .groupNo(groupNo)
        .groupOrder(groupOrder).build();
    
    blogMapper.updateGroupOrder(blogCommentParentDTO);

    // 블로그 정보
    int blogNo = Integer.parseInt(request.getParameter("blogNo"));
    
    // 답글 정보
    String contents = securityUtils.preventXss(request.getParameter("contents"));

    BlogCommentDTO blogCommentChildDTO = BlogCommentDTO.builder()
        .userNo(loginUser.getUserNo())
        .blogNo(blogNo)
        .contents(contents)
        .depth(depth + 1)
        .groupNo(groupNo)
        .groupOrder(groupOrder + 1).build();
    
    int insertResult = blogMapper.insertBlogCommentChild(blogCommentChildDTO);
    return ResponseEntity.ok(Map.of("isSuccess", insertResult == 1));
    
  }
  
}
