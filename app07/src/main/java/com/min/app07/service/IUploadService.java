package com.min.app07.service;

import java.util.List;

import org.springframework.ui.Model;

import com.min.app07.dto.UploadDTO;

import jakarta.servlet.http.HttpServletRequest;

public interface IUploadService {
  int registerUpload(HttpServletRequest request);
  List<UploadDTO> getUploadList();
  void loadUpload(int uploadNo, Model model);
}
