package com.pcz.cheer.config;

import io.minio.MinioClient;
import io.minio.policy.PolicyType;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;

import java.util.Map;

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
     * bucket
     */
    private String bucket;

    @Bean
    public MinioClient minioClient() {
        try {
            MinioClient minioClient = new MinioClient(url, accessKey, secretKey);
            if (!minioClient.bucketExists(bucket)) {
                minioClient.makeBucket(bucket);
                log.info("create bucket");
            }

            // 设置连接可永久访问
            minioClient.setBucketPolicy(bucket, "**", PolicyType.READ_ONLY);
            log.info("set bucket policy");

            return minioClient;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
