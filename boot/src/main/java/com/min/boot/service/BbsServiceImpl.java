package com.min.boot.service;

import org.springframework.stereotype.Service;

import com.min.boot.mapper.IBbsMapper;
import com.min.boot.utils.PageUtils;
import com.min.boot.utils.SecurityUtils;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class BbsServiceImpl implements IBbsService {

  private final IBbsMapper bbsMapper;
  private final PageUtils pageUtils;
  private final SecurityUtils securityUtils;
  
}
