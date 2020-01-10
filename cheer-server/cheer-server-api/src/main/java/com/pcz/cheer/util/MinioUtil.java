package com.pcz.cheer.util;

import com.pcz.cheer.config.MinioConfig;
import io.minio.MinioClient;
import io.minio.ObjectStat;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import java.io.InputStream;

/**
 * @author picongzhi
 */
@EnableConfigurationProperties(MinioConfig.class)
@Configuration
@Slf4j
public class MinioUtil {
    @Autowired
    private MinioClient minioClient;

    /**
     * 保存文件
     *
     * @param bucketName  bucket名
     * @param objectName  对象名
     * @param inputStream 输入流
     * @param size        流的大小
     * @param contentType 文件类型
     * @throws Exception Exception
     */
    public void saveFile(String bucketName, String objectName, InputStream inputStream, long size, String contentType) throws Exception {
        minioClient.putObject(bucketName, objectName, inputStream, size, contentType);
    }

    /**
     * 获取文件
     *
     * @param bucketName bucket名
     * @param objectName 对象名
     * @return InputStream
     * @throws Exception Exception
     */
    public InputStream getFile(String bucketName, String objectName) throws Exception {
        return minioClient.getObject(bucketName, objectName);
    }

    /**
     * 获取文件类型
     *
     * @param bucketName bucket名
     * @param objectName 对象名
     * @return 文件类型
     * @throws Exception Exception
     */
    public String getFileContentType(String bucketName, String objectName) throws Exception {
        return minioClient.statObject(bucketName, objectName).contentType();
    }

    /**
     * 获取文件url地址
     *
     * @param bucketName bucket名
     * @param objectName 对象名
     * @return url
     * @throws Exception Exception
     */
    public String getFileUrl(String bucketName, String objectName) throws Exception {
        return minioClient.getObjectUrl(bucketName, objectName);
    }
}
