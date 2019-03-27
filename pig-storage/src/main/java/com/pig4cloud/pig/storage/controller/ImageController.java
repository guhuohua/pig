package com.pig4cloud.pig.storage.controller;

import com.pig4cloud.pig.storage.entity.UploadRes;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.util.DigestUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.PostConstruct;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;


/**
 * @auther: 球球
 * @Date: 2019/2/25 15:22
 * @description:
 */
@Slf4j
@RestController
@RequestMapping("/image")
public class ImageController {

	@Value("${upload.imgRootFolder}")
	private String imgRootFolder;
	@Value("${upload.fileSize}")
	private Integer fileSize;
	@Value("${upload.urlPath}")
	private String urlPath;
	@Value("${upload.imgPath}")
	private String imgPath;

	@PostConstruct
	public void init(){
		File file = new File(imgRootFolder);
		if (!file.exists()) {
			final boolean b = file.mkdirs();
			if (b) {
				log.error("图片存储目录初始化失败! 路径：{}", imgRootFolder);
			}
		}
	}


	@RequestMapping("/create")
	public Object upload(MultipartFile file) throws Exception {
		FileOutputStream stream = null;
		String fileName;
		if (file.getSize() > fileSize * 1024 * 1024) {
			throw new Exception("最大文件不能超过" + fileSize + "MB");
		}
		try {
			fileName = file.getOriginalFilename();
			int index = fileName.lastIndexOf(".");
			String fileSuffix = fileName.substring(index);
			byte[] bytes = file.getBytes();
			fileName = DigestUtils.md5DigestAsHex(bytes) + fileSuffix;
			String filePath = imgRootFolder + "/" + fileName;
			File img = new File(filePath);
			stream = new FileOutputStream(img);
			stream.write(bytes);
			stream.close();
		} catch (IOException e) {
			e.printStackTrace();
			throw new Exception("上传失败，请重试！");
		} finally {
			try {
				if (stream != null)
					stream.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		String picPath = urlPath + "/" + imgPath + "/" + fileName;
//		return R.ok(new UploadRes(picPath, fileName));
		return new UploadRes(picPath, fileName);
	}
}
