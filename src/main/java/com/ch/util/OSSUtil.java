package com.ch.util;

import com.aliyun.oss.ClientException;
import com.aliyun.oss.OSSClient;
import com.aliyun.oss.OSSException;
import com.aliyun.oss.model.*;
import com.ch.config.OssProperties;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.*;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;

@Component
public class OSSUtil {

    private static String FILE_URL;

    //log
    private final Logger LOG = LoggerFactory.getLogger(OSSUtil.class);
    @Autowired
    OssProperties ossProperties;

    /**
     * 获取阿里云OSS客户端对象
     */
    public OSSClient getOSSClient() {
        return new OSSClient(ossProperties.getOssaddress(), ossProperties.getAccessKeyId(), ossProperties.getAccessKeySecret());
    }

    /**
     * 新建Bucket  --Bucket权限:私有
     *
     * @param bucketName bucket名称
     * @return true 新建Bucket成功
     */
    public boolean createBucket(OSSClient client, String bucketName) {
        Bucket bucket = client.createBucket(bucketName);
        return bucketName.equals(bucket.getName());
    }

    /**
     * 删除Bucket
     *
     * @param bucketName bucket名称
     */
    public void deleteBucket(OSSClient client, String bucketName) {
        client.deleteBucket(bucketName);
    }

    /**
     * 向阿里云的OSS存储中存储文件  --file也可以用InputStream替代
     *
     * @param client OSS客户端
     * @param file   上传文件
     * @return String 唯一MD5数字签名
     */
    public String uploadObject2OSS(OSSClient client, File file) {
        String resultStr = null;
        try {
            InputStream is = new FileInputStream(file);
            String fileName = file.getName();
            Long fileSize = file.length();
            //创建上传Object的Metadata
            ObjectMetadata metadata = new ObjectMetadata();
            metadata.setContentLength(is.available());
            metadata.setCacheControl("no-cache");
            metadata.setHeader("Pragma", "no-cache");
            metadata.setContentEncoding("utf-8");
            metadata.setContentType(getContentType(fileName));
            metadata.setContentDisposition("filename/filesize=" + fileName + "/" + fileSize + "Byte.");
            //上传文件
            PutObjectResult putResult = client.putObject(ossProperties.getBucketName(), ossProperties.getFilePath() + fileName, is, metadata);
            //解析结果
            resultStr = putResult.getETag();
            is.close();
        } catch (Exception e) {
            LOG.error("上传阿里云OSS服务器异常." + e.getMessage(), e);
        }
        return resultStr;
    }

    /**
     * 根据key获取OSS服务器上的文件输入流
     *
     * @param client     OSS客户端
     * @param bucketName bucket名称
     * @param filePath   文件路径
     * @param key        Bucket下的文件的路径名+文件名
     */
    public InputStream getOSS2InputStream(OSSClient client, String bucketName, String filePath, String key) {
        OSSObject ossObj = client.getObject(bucketName, filePath + key);
        return ossObj.getObjectContent();
    }

    /**
     * 根据key删除OSS服务器上的文件
     *
     * @param client     OSS客户端
     * @param bucketName bucket名称
     * @param filePath   文件路径
     * @param key        Bucket下的文件的路径名+文件名
     */
    public void deleteFile(OSSClient client, String bucketName, String filePath, String key) {
        client.deleteObject(bucketName, filePath + key);
        LOG.info("删除" + bucketName + "下的文件" + filePath + key + "成功");
    }

    /**
     * 通过文件名判断并获取OSS服务文件上传时文件的contentType
     *
     * @param fileName 文件名
     * @return 文件的contentType
     */
    public final String getContentType(String fileName) {
        String fileExtension = fileName.substring(fileName.lastIndexOf(".") + 1);
        if ("bmp".equalsIgnoreCase(fileExtension)) return "image/bmp";
        if ("gif".equalsIgnoreCase(fileExtension)) return "image/gif";
        if ("jpeg".equalsIgnoreCase(fileExtension) || "jpg".equalsIgnoreCase(fileExtension)) return "image/jpeg";
        if ("png".equalsIgnoreCase(fileExtension)) return "image/png";
        if ("html".equalsIgnoreCase(fileExtension)) return "text/html";
        if ("txt".equalsIgnoreCase(fileExtension)) return "text/plain";
        if ("vsd".equalsIgnoreCase(fileExtension)) return "application/vnd.visio";
        if ("ppt".equalsIgnoreCase(fileExtension) || "pptx".equalsIgnoreCase(fileExtension))
            return "application/vnd.ms-powerpoint";
        if ("doc".equalsIgnoreCase(fileExtension) || "docx".equalsIgnoreCase(fileExtension))
            return "application/msword";
        if ("xml".equalsIgnoreCase(fileExtension)) return "text/xml";
        return "text/html";
    }

    /**
     * 销毁
     */
    public void destory(OSSClient client) {
        client.shutdown();
    }

    public String upload(MultipartFile file) {
        String filename = file.getOriginalFilename();
        try {

            if (file != null) {
                if (!"".equals(filename.trim())) {
                    File newFile = new File(filename);
                    FileOutputStream os = new FileOutputStream(newFile);
                    os.write(file.getBytes());
                    os.close();
                    file.transferTo(newFile);
                    // 上传到OSS
                    boolean isImage = true;
                    try {
                        Image image = ImageIO.read(newFile);
                        isImage = image == null ? false : true;
                    } catch (IOException e) {
                        e.printStackTrace();
                    }


                    SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
                    String dateStr = format.format(new Date());

                    // 判断文件
                    if (file == null) {
                        return null;
                    }
                    // 创建OSSClient实例。
                    OSSClient ossClient = new OSSClient(ossProperties.getOssaddress(), ossProperties.getAccessKeyId(), ossProperties.getAccessKeySecret());
                    try {
                        // 判断容器是否存在,不存在就创建
                        if (!ossClient.doesBucketExist(ossProperties.getBucketName())) {
                            ossClient.createBucket(ossProperties.getBucketName());
                            CreateBucketRequest createBucketRequest = new CreateBucketRequest(ossProperties.getBucketName());
                            createBucketRequest.setCannedACL(CannedAccessControlList.PublicRead);
                            ossClient.createBucket(createBucketRequest);
                        }
                        // 设置文件路径和名称
                        String fileUrl = ossProperties.getFilehost() + "/" + (dateStr + "/" + UUID.randomUUID().toString().replace("-", "") + "-" + file.getName());
                        if (isImage) {//如果是图片，则图片的URL为：....
                            FILE_URL = "https://" + ossProperties.getBucketName() + "." + ossProperties.getOssaddress() + "/" + fileUrl;
                        } else {
                            FILE_URL = "非图片，不可预览。文件路径为：" + fileUrl;
                        }

                        // 上传文件
                        PutObjectResult result = ossClient.putObject(new PutObjectRequest(ossProperties.getBucketName(), fileUrl, newFile));
                        // 设置权限(公开读)
                        ossClient.setBucketAcl(ossProperties.getBucketName(), CannedAccessControlList.PublicRead);
                        if (result != null) {
                        }
                    } catch (OSSException oe) {
                    } catch (ClientException ce) {
                    } finally {
                        if (ossClient != null) {
                            ossClient.shutdown();
                        }
                    }
                    return FILE_URL;
                }

            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return FILE_URL;
    }
}
