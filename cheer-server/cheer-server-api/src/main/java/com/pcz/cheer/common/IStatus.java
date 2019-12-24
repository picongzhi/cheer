package com.pcz.cheer.common;

/**
 * @author picongzhi
 */
public interface IStatus {
    /**
     * 获取状态码
     *
     * @return 状态码
     */
    Integer getCode();

    /**
     * 获取返回信息
     *
     * @return 返回信息
     */
    String getMessage();
}
