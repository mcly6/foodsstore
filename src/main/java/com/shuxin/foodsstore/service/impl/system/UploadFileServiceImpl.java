package com.shuxin.foodsstore.service.impl.system;

import com.shuxin.foodsstore.entity.system.UploadFile;
import com.shuxin.foodsstore.repository.system.UploadFileRepository;
import com.shuxin.foodsstore.service.system.UploadFileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UploadFileServiceImpl implements UploadFileService {

    @Autowired
    private UploadFileRepository uploadFileRepository;


    @Override
    public UploadFile save(UploadFile uploadFile) {
        return uploadFileRepository.save(uploadFile);
    }

    @Override
    public UploadFile findOne(String fileId) {
        return uploadFileRepository.findOne(fileId);
    }
}
