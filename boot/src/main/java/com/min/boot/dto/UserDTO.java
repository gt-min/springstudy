package com.min.boot.dto;

import java.sql.Date;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data
public class UserDTO {
  private int userNo;
  private String email;
  private String pw;
  private String name;
  private String gender;
  private String mobile;
  private int sns;
  private Date pwModifyDt;
  private Date signupDt;
}
