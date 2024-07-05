package com.min.app07.task;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.min.app07.service.IUploadService;

@Component
public class ScheduleTask {

  @Autowired
  private IUploadService uploadService;

  @Scheduled(cron = "0 10 17 * * ?")
  public void removeTmpFiles() {
    uploadService.removeTmpFiles();
  }
  
}
