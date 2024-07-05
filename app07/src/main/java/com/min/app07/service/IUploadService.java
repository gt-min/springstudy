package com.min.app07.service;

import java.util.List;

import org.springframework.core.io.Resource;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.min.app07.dto.UploadDTO;

import jakarta.servlet.http.HttpServletRequest;

public interface IUploadService {
  int registerUpload1(HttpServletRequest request);
  int registerUpload2(MultipartHttpServletRequest multipartRequest);
  List<UploadDTO> getUploadList();
  void loadUpload(int uploadNo, Model model);
  ResponseEntity<Resource> download(String userAgent, int fileNo);
  ResponseEntity<Resource> downloadAll(int uploadNo);
  void removeTmpFiles();
}
