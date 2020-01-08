package com.pcz.cheer.config;

import io.minio.MinioClient;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;

/**
 * @author picongzhi
 */
@ConfigurationProperties(prefix = "minio")
@Data
@Slf4j
public class MinioConfig {
    /**
     * minio服务地址
     */
    private String url;

    /**
     * ACCESS KEY
     */
    private String accessKey;

    /**
     * SECRET KEY
     */
    private String secretKey;

    /**
     * 头像文件bucket
     */
    private String avatarBucket;

    @Bean
    public MinioClient minioClient() {
        try {
            MinioClient minioClient = new MinioClient(url, accessKey, secretKey);
            if (!minioClient.bucketExists(avatarBucket)) {
                minioClient.makeBucket(avatarBucket);
                log.info("create avatar bucket");
            }

            return minioClient;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
