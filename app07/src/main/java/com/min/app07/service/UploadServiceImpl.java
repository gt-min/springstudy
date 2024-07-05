package com.min.app07.service;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.net.URLEncoder;
import java.util.Arrays;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

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
      
      // down_count 증가
      uploadMapper.updateDownCount(fileNo);
      
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
  
  @Override
  public ResponseEntity<Resource> downloadAll(int uploadNo) {
    
    // 다운로드 대상 파일들
    List<FileDTO> fileList = uploadMapper.getFileList(uploadNo);
    
    // 파일이 없으면 다운로드 종료
    if(fileList.isEmpty()) {
      return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
    
    // 임시 파일(압축 파일)을 저장한 경로
    File tmpDir = new File("/tmp");
    if(!tmpDir.exists())
      tmpDir.mkdirs();
    
    // 임시 파일(압축 파일) 이름
    String tmpFilename = System.currentTimeMillis() + ".zip";
    
    // 임시 파일(압축 파일)
    File tmpFile = new File(tmpDir, tmpFilename);
    
    try {
      
      // zip 파일 만들기 시작
      ZipOutputStream out = new ZipOutputStream(new FileOutputStream(tmpFile));
      
      // 파일을 하나씩 zip 파일에 추가하기
      fileList.stream().forEach(fileDTO -> {
        
        try {
          
          // ZipEntry 객체 생성
          ZipEntry zipEntry = new ZipEntry(fileDTO.getOriginalFilename());
          
          // ZipEntry 객체 등록
          out.putNextEntry(zipEntry);  // 파일명만 zip 안에 등록한 상태
          
          // 파일 등록 (zip 안에 넣을 파일을 읽어서 byte[] 배열에 저장)
          File file = new File(fileDTO.getUploadPath(), fileDTO.getFilesystemName());
          BufferedInputStream in = new BufferedInputStream(new FileInputStream(file));
          byte[] b = in.readAllBytes();
          out.write(b);
          
          // 리소스 정리
          in.close();
          out.closeEntry();  // ZipEntry 완성
          
        } catch (Exception e) {
          e.printStackTrace();
        }
        
      });
      
      // zip 파일 완성
      out.close();
      
    } catch (Exception e) {
      e.printStackTrace();
    }
    
    // down_count 증가
    uploadMapper.updateDownCountAll(uploadNo);
    
    // 다운로드 응답 헤더 설정
    HttpHeaders headers = new HttpHeaders();
    headers.add("Content-Disposition", "attachment; filename=" + fileList.get(0).getOriginalFilename() + " etc" + (fileList.size() - 1) + "ea.zip");
    
    return new ResponseEntity<>(new FileSystemResource(tmpFile), headers, HttpStatus.OK);
    
  }
  
  @Override
  public void removeTmpFiles() {
    File tmpDir = new File("/tmp");
    File[] tmpFiles = tmpDir.listFiles();
    if(tmpFiles != null) {
      Arrays.stream(tmpFiles).forEach(tmpFile -> tmpFile.delete());
    }
  }
  
}
