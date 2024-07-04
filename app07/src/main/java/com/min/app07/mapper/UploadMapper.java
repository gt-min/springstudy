package com.min.app07.mapper;

import org.apache.ibatis.annotations.Mapper;

import com.min.app07.dto.FileDTO;
import com.min.app07.dto.UploadDTO;

@Mapper
public interface UploadMapper {
  int insertUpload(UploadDTO upload);
  int insertFile(FileDTO file);
}
