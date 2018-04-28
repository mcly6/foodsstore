package com.shuxin.foodsstore.service.system;

import com.shuxin.foodsstore.entity.system.UploadFile;

public interface UploadFileService {

   UploadFile save(UploadFile uploadFile);

   UploadFile findOne(String fileId);
}
