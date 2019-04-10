package com.ch.controller;

import com.ch.base.ResponseResult;
import com.ch.base.UploadName;
import com.ch.service.UploadService;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping(value = "base")
@Api(value = "基础")
public class UploadController {

    @Autowired
    UploadService uploadService;

    @PostMapping(value = "upload")
    public UploadName upload(MultipartFile file) {
        return uploadService.upload(file);
    }

    @PostMapping(value = "uploadFile")
    public ResponseResult uploadFile(MultipartFile file) {
        ResponseResult result = new ResponseResult();
        try {
            result =  uploadService.uploadFile(file);
            result.setCode(410);
        } catch (Exception e) {
            e.printStackTrace();
            result.setCode(500);
            result.setError(e.getMessage());
            result.setError_description("图片上传失败");
        }
        return result;
    }
}
