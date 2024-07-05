package com.min.app07.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data
public class FileDTO {
  private int fileNo;
  private String uploadPath;
  private String originalFilename;
  private String filesystemName;
  private int uploadNo;
  private int downCount;
}
