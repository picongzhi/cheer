package com.pcz.cheer.config;

import com.google.common.collect.Lists;
import lombok.Data;

import java.util.List;

/**
 * @author picongzhi
 */
@Data
public class IgnoreConfig {
    /**
     * 需要忽略的URL格式，不考虑请求方法
     */
    private List<String> pattern = Lists.newArrayList();

    /**
     * 需要忽略的GET请求
     */
    private List<String> get = Lists.newArrayList();

    /**
     * 需要忽略的POST请求
     */
    private List<String> post = Lists.newArrayList();

    /**
     * 需要忽略的PUT请求
     */
    private List<String> put = Lists.newArrayList();

    /**
     * 需要忽略的DELETE请求
     */
    private List<String> delete = Lists.newArrayList();

    /**
     * 需要忽略的HEAD请求
     */
    private List<String> head = Lists.newArrayList();

    /**
     * 需要忽略的PATCH请求
     */
    private List<String> patch = Lists.newArrayList();

    /**
     * 需要忽略的OPTIONS请求
     */
    private List<String> options = Lists.newArrayList();

    /**
     * 需要忽略的TRACE请求
     */
    private List<String> trace = Lists.newArrayList();
}
