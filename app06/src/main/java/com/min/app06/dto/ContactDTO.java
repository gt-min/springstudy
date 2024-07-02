package com.min.app06.dto;

import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data
public class ContactDTO {
  
  private int contactNo;
  private String name;
  private String email;
  private String mobile;
  
  @DateTimeFormat(pattern = "yyyy-MM-dd")
  private Date createdAt;
  
}
