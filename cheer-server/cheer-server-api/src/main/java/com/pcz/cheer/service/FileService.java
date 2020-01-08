package com.pcz.cheer.service;

import java.io.InputStream;

/**
 * @author picongzhi
 */
public interface FileService {
    void save(InputStream inputStream, long size, String contentType) throws Exception;
}
