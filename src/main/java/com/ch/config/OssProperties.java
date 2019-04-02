package com.ch.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties(prefix = "oss")
public class OssProperties {
    private String ossaddress;

    public String getOssaddress() {
        return ossaddress;
    }

    public void setOssaddress(String ossaddress) {
        this.ossaddress = ossaddress;
    }

    private String accessKeyId;
    private String accessKeySecret;
    private String bucketName;
    private String filePath;
    private String filehost;

    public String getFilehost() {
        return filehost;
    }

    public void setFilehost(String filehost) {
        this.filehost = filehost;
    }

    public String getBucketName() {
        return bucketName;
    }

    public void setBucketName(String bucketName) {
        this.bucketName = bucketName;
    }

    public String getFilePath() {
        return filePath;
    }

    public void setFilePath(String filePath) {
        this.filePath = filePath;
    }


    public void setAccessKeyId(String accessKeyId) {
        this.accessKeyId = accessKeyId;
    }

    public void setAccessKeySecret(String accessKeySecret) {
        this.accessKeySecret = accessKeySecret;
    }


    public String getAccessKeyId() {
        return accessKeyId;
    }

    public String getAccessKeySecret() {
        return accessKeySecret;
    }

}
