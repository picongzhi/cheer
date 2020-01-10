package com.pcz.cheer.service;

import com.pcz.cheer.vo.FileInfo;

import java.io.InputStream;

/**
 * @author picongzhi
 */
public interface FileService {
    /**
     * 保存文件
     *
     * @param inputStream 输入流
     * @param size        文件大小
     * @param contentType 文件类型
     * @return FileInfo
     * @throws Exception Exception
     */
    FileInfo save(InputStream inputStream, long size, String contentType) throws Exception;
}
