//package com.ch.ch;
//
//import com.ch.model.UserDTO;
//import com.ch.service.BtSysUserService;
//import com.ch.service.BtViewMenuService;
//import com.ch.util.OSSUtil;
//import org.apache.http.entity.ContentType;
//import org.junit.Test;
//import org.junit.runner.RunWith;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.mock.web.MockMultipartFile;
//import org.springframework.test.context.junit4.SpringRunner;
//import org.springframework.web.multipart.MultipartFile;
//
//import java.io.File;
//import java.io.FileInputStream;
//import java.io.IOException;
//
//@RunWith(SpringRunner.class)
//@SpringBootTest
//public class ChApplicationTests {
//
//    @Autowired
//    OSSUtil ossUtil;
//
//    @Autowired
//    BtSysUserService btSysUserService;
//
//    @Autowired
//    BtViewMenuService btViewMenuService;
//
//    @Test
//    public void testUploadImage(){
//        File file = new File("/Users/changhan/Downloads/640.gif");
//        try {
//            MultipartFile mulFile = new MockMultipartFile("640.gif", "640.gif", ContentType.APPLICATION_OCTET_STREAM.toString(), new FileInputStream(file));
//            String upload = ossUtil.upload(mulFile);
//            String originalFilename = mulFile.getOriginalFilename();
//            System.out.println(upload);
//            System.out.println(originalFilename);
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//    }
//
//
//}
