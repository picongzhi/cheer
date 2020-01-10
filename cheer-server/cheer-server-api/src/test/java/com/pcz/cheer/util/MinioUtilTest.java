package com.pcz.cheer.util;

import cn.hutool.core.util.IdUtil;
import com.pcz.cheer.ApplicationTest;
import com.pcz.cheer.config.MinioConfig;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.InputStream;

@Slf4j
public class MinioUtilTest extends ApplicationTest {
    @Autowired
    private MinioConfig minioConfig;

    @Autowired
    private MinioUtil minioUtil;

    @Test
    public void saveFileTest() throws Exception {
        InputStream inputStream = Thread.currentThread().getContextClassLoader().getResourceAsStream("images/IMG_1940.JPG");
        String objectName = IdUtil.fastUUID();
        minioUtil.saveFile(minioConfig.getBucket(), objectName, inputStream, (long) inputStream.available(), "image/jpeg");
    }

    @Test
    public void getFileContentTypeTest() throws Exception {
        String objectName = "e96a22aa-f7d3-4c23-a0fa-294dc0577178";
        String contentType = minioUtil.getFileContentType(minioConfig.getBucket(), objectName);
        log.info("content type: {}", contentType);
    }
}
