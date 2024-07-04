package com.min.app07.service;

import java.io.File;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
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
  public int registerUpload(HttpServletRequest request) {
    
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
  public List<UploadDTO> getUploadList() {
    return uploadMapper.getUploadList();
  }
  
  @Override
  public void loadUpload(int uploadNo, Model model) {
    model.addAttribute("upload", uploadMapper.getUploadByNo(uploadNo));
    model.addAttribute("fileList", uploadMapper.getFileList(uploadNo));
  }

}
