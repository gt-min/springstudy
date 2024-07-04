package com.min.app07.dto;

import java.sql.Date;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data
public class UploadDTO {
  private int uploadNo;
  private String uploader;
  private String ip;
  private Date uploadDt;
  
  private int fileCnt;
}
