package com.ch.controller;

import com.ch.base.ResponseResult;
import com.ch.base.UploadName;
import com.ch.service.BtUploadService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping(value = "bt")
public class BtUploadController {

    @Autowired
    BtUploadService btUploadService;

    @PostMapping(value = "upload")
    public UploadName upload(MultipartFile file) {
        return btUploadService.upload(file);
    }

    @PostMapping(value = "uploadFile")
    public ResponseResult uploadFile(MultipartFile file) {
        return btUploadService.uploadFile(file);
    }
}
