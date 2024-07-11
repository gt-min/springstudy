package com.min.boot.service;

import org.springframework.ui.Model;

import jakarta.servlet.http.HttpServletRequest;

public interface IBbsService {
  int saveBbsParent(HttpServletRequest request);
  void loadBbsList(HttpServletRequest request, Model model);
}
