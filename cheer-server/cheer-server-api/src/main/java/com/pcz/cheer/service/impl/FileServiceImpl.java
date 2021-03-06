package com.pcz.cheer.service.impl;

import cn.hutool.core.util.IdUtil;
import com.pcz.cheer.config.MinioConfig;
import com.pcz.cheer.service.FileService;
import com.pcz.cheer.util.MinioUtil;
import com.pcz.cheer.vo.FileInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.InputStream;

/**
 * @author picongzhi
 */
@Service
public class FileServiceImpl implements FileService {
    @Autowired
    private MinioConfig minioConfig;

    @Autowired
    private MinioUtil minioUtil;

    @Override
    public FileInfo save(InputStream inputStream, long size, String contentType) throws Exception {
        String objectName = IdUtil.randomUUID();
        String url = minioUtil.saveFile(minioConfig.getBucket(), objectName, inputStream, size, contentType);

        return new FileInfo(url);
    }
}
