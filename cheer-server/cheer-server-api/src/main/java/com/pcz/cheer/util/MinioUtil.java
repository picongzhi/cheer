package com.pcz.cheer.util;

import com.pcz.cheer.config.MinioConfig;
import io.minio.MinioClient;
import io.minio.errors.InvalidEndpointException;
import io.minio.errors.InvalidPortException;
import io.minio.messages.Bucket;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author picongzhi
 */
@EnableConfigurationProperties(MinioConfig.class)
@Configuration
@Slf4j
public class MinioUtil {
    @Autowired
    private MinioConfig minioConfig;

    @Autowired
    private MinioClient minioClient;
}
