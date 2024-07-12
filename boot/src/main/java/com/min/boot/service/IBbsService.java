package com.min.boot.service;

import org.springframework.ui.Model;

import jakarta.servlet.http.HttpServletRequest;

public interface IBbsService {
  int saveBbsParent(HttpServletRequest request);
  void loadBbsList(HttpServletRequest request, Model model);
  int saveBbsChild(HttpServletRequest request);
  int removeBbs(int bbsNo);
  void loadFindList(HttpServletRequest request, Model model);
}
