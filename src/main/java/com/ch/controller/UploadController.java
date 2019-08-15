package com.ch.controller;

import com.ch.base.ResponseResult;
import com.ch.base.UploadName;
import com.ch.service.UploadService;
import com.ch.util.TokenUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@RestController
@RequestMapping(value = "base")
@Api(tags = "数据导入")
public class UploadController {

    @Autowired
    UploadService uploadService;

    @PostMapping(value = "upload")
    public UploadName upload(MultipartFile file) {
        return uploadService.upload(file);
    }

    @PostMapping(value = "uploadFile")
    @CrossOrigin
    public ResponseResult uploadFile(HttpServletResponse response, MultipartFile file) {
        response.setHeader("Access-Control-Allow-Origin", "*");
        ResponseResult result = new ResponseResult();
        try {
            result = uploadService.uploadFile(file);
            result.setCode(410);
        } catch (Exception e) {
            e.printStackTrace();
            result.setCode(500);
            result.setError(e.getMessage());
            result.setError_description("图片上传失败");
        }
        return result;
    }


    @PostMapping(value = "uploadGoods")
    @CrossOrigin
    @ApiOperation("导入商品数据")
    public ResponseResult uploadGoods(HttpServletRequest req, HttpServletResponse response, MultipartFile file) {
        response.setHeader("Access-Control-Allow-Origin", "*");
        ResponseResult result = new ResponseResult();
        try {
            String token = req.getHeader("Authorization");
            Integer userId = TokenUtil.getUserId(token);
            result = uploadService.uploadGoods(file, userId);
            result.setCode(410);
        } catch (Exception e) {
            e.printStackTrace();
            result.setCode(500);
            result.setError(e.getMessage());
            result.setError_description("excel导入失败");
        }
        return result;
    }

    @PostMapping(value = "uploadSpecification")
    @CrossOrigin
    @ApiOperation("导入规格数据")
    public ResponseResult uploadSpecification(HttpServletRequest req, HttpServletResponse response, MultipartFile file) {
        response.setHeader("Access-Control-Allow-Origin", "*");
        ResponseResult result = new ResponseResult();
        try {
            String token = req.getHeader("Authorization");
            Integer userId = TokenUtil.getUserId(token);
            result = uploadService.uploadSpecification(file, userId);
            result.setCode(410);
        } catch (Exception e) {
            e.printStackTrace();
            result.setCode(500);
            result.setError(e.getMessage());
            result.setError_description("excel导入失败");
        }
        return result;
    }
}
