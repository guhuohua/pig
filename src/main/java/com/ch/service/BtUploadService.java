package com.ch.service;

import com.ch.base.ResponseResult;
import com.ch.base.UploadName;
import org.springframework.web.multipart.MultipartFile;

public interface BtUploadService {

    /**
     * 文件上传
     * @param file
     * @return
     */
    UploadName upload(MultipartFile file);

    ResponseResult uploadFile(MultipartFile file);
}
