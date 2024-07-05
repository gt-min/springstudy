package com.min.app07.service;

import java.io.File;
import java.net.URLEncoder;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.multipart.MultipartResolver;
import org.springframework.web.multipart.support.StandardServletMultipartResolver;

import com.min.app07.dto.FileDTO;
import com.min.app07.dto.UploadDTO;
import com.min.app07.mapper.UploadMapper;
import com.min.app07.utils.FileUploadUtils;

import jakarta.servlet.http.HttpServletRequest;

@Service
public class UploadServiceImpl implements IUploadService {

  private UploadMapper uploadMapper;
  private FileUploadUtils fileUploadUtils;
  
  @Autowired
  public UploadServiceImpl(UploadMapper uploadMapper, FileUploadUtils fileUploadUtils) {
    super();
    this.uploadMapper = uploadMapper;
    this.fileUploadUtils = fileUploadUtils;
  }

  @Override
  public int registerUpload1(HttpServletRequest request) {
    
    MultipartResolver resolver = new StandardServletMultipartResolver();
    MultipartHttpServletRequest multipartRequest = resolver.resolveMultipart(request);    
    
    String uploader = request.getParameter("uploader");
    String ip = request.getRemoteAddr();
    UploadDTO upload = UploadDTO.builder()
        .uploader(uploader)
        .ip(ip)
        .build();
    uploadMapper.insertUpload(upload);  // UploadMapper 에서 upload 에 uploadNo 를 저장한다.
     
    int uploadNo = upload.getUploadNo();
    String uploadPath = fileUploadUtils.getUploadPath();
    File uploadDir = new File(uploadPath);
    if(!uploadDir.exists())
      uploadDir.mkdirs();
    List<MultipartFile> files = multipartRequest.getFiles("files");
    files.stream().forEach(file -> {
      String originalFilename = file.getOriginalFilename();
      String filesystemName = fileUploadUtils.getFilesystemName(originalFilename);
      File uploadFile = new File(uploadDir, filesystemName);
      try { 
        file.transferTo(uploadFile);
      } catch (Exception e) {
        e.printStackTrace();
      }
      FileDTO fileDTO = FileDTO.builder()
          .uploadPath(uploadPath)
          .originalFilename(originalFilename)
          .filesystemName(filesystemName)
          .uploadNo(uploadNo)
          .build();
      uploadMapper.insertFile(fileDTO);
    });
    
    return 1;
    
  }
  
  @Override
  public int registerUpload2(MultipartHttpServletRequest multipartRequest) {
    
    /* upload_t 에 insert 하기 */
    String uploader = multipartRequest.getParameter("uploader");
    String ip = multipartRequest.getRemoteAddr();
    UploadDTO upload = UploadDTO.builder()
        .uploader(uploader)
        .ip(ip)
        .build();
    uploadMapper.insertUpload(upload);
    
    /* 첨부 파일 저장하기 + filt_t 에 insert 하기 */
    int uploadNo = upload.getUploadNo();
    String uploadPath = fileUploadUtils.getUploadPath();
    File uploadDir = new File(uploadPath);
    if(!uploadDir.exists())
      uploadDir.mkdirs();
    List<MultipartFile> multipartFiles = multipartRequest.getFiles("files");
    multipartFiles.stream().forEach(multipartFile -> {
      String originalFilename = multipartFile.getOriginalFilename();
      String filesystemName = fileUploadUtils.getFilesystemName(originalFilename);
      File uploadFile = new File(uploadDir, filesystemName);
      try {        
        multipartFile.transferTo(uploadFile);  // 실제 업로드가 진행되는 코드
      } catch (Exception e) {
        e.printStackTrace();
      }
      FileDTO file = FileDTO.builder()
          .uploadPath(uploadPath)
          .originalFilename(originalFilename)
          .filesystemName(filesystemName)
          .uploadNo(uploadNo)
          .build();
      uploadMapper.insertFile(file);
    });
    
    return 1;
    
  }
  
  @Override
  public List<UploadDTO> getUploadList() {
    return uploadMapper.getUploadList();
  }
  
  @Override
  public void loadUpload(int uploadNo, Model model) {
    model.addAttribute("upload", uploadMapper.getUploadByNo(uploadNo));
    model.addAttribute("fileList", uploadMapper.getFileList(uploadNo));
  }

  @Override
  public ResponseEntity<Resource> download(String userAgent, int fileNo) {
    
    Resource resource = null;
    String originalFilename = null;
    
    try {
      
      FileDTO file = uploadMapper.getFileByNo(fileNo);
      String uploadPath = file.getUploadPath();
      String filesystemName = file.getFilesystemName();
      originalFilename = file.getOriginalFilename();
      
      File target = new File(uploadPath, filesystemName);
      resource = new FileSystemResource(target);
      
      // 첨부 파일 존재 여부 확인 (없으면 다운로드 취소하기)
      if(!resource.exists()) {
        return new ResponseEntity<Resource>(resource, HttpStatus.NOT_FOUND);
      }
      
      // 다운로드 카운트 file_t 테이블에 등록하기
      
      // 사용자가 다운로드 받을 파일명 결정
      // IE
      if(userAgent.contains("Trident")) {
        originalFilename = URLEncoder.encode(originalFilename, "UTF-8").replace("+", " ");
      }
      // Edge
      else if(userAgent.contains("Edg")) {
        originalFilename = URLEncoder.encode(originalFilename, "UTF-8");      
      }
      // Other
      else {
        originalFilename = new String(originalFilename.getBytes("UTF-8"), "ISO-8859-1");
      }
      
    } catch (Exception e) {
      e.printStackTrace();
    }
    
    // 다운로드 응답 헤더 설정
    HttpHeaders headers = new HttpHeaders();
    headers.add("Content-Disposition", "attachment; filename=" + originalFilename);
    
    return new ResponseEntity<Resource>(resource, headers, HttpStatus.OK);
    
  }
  
  
  
  
  
  
  
  
}
