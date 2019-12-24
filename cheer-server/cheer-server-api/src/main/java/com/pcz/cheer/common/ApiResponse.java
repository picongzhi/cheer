package com.pcz.cheer.common;

import lombok.Data;

import java.io.Serializable;

/**
 * @author picongzhi
 */
@Data
public class ApiResponse implements Serializable {
    /**
     * 状态码
     */
    private Integer code;

    /**
     * 返回消息
     */
    private String message;

    /**
     * 返回数据
     */
    private Object data;

    private ApiResponse() {
    }

    private ApiResponse(Integer code, String message, Object data) {
        this.code = code;
        this.message = message;
        this.data = data;
    }

    /**
     * 自定义的API返回
     *
     * @param code    状态码
     * @param message 消息
     * @param data    数据
     * @return
     */
    public static ApiResponse of(Integer code, String message, Object data) {
        return new ApiResponse(code, message, data);
    }

    /**
     * 成功且不带数据的API返回
     *
     * @return ApiResponse
     */
    public static ApiResponse ofSuccess() {
        return ofSuccess(null);
    }

    /**
     * 成功且带有数据的API返回
     *
     * @param data
     * @return
     */
    public static ApiResponse ofSuccess(Object data) {
        return ofStatus(Status.SUCCESS, data);
    }

    /**
     * 成功且带有自定义消息的API返回
     *
     * @param message 消息
     * @return ApiResponse
     */
    public static ApiResponse ofMessage(String message) {
        return of(Status.SUCCESS.getCode(), message, null);
    }

    /**
     * 带状态的API返回
     *
     * @param status 状态
     * @return ApiResponse
     */
    public static ApiResponse ofStatus(Status status) {
        return ofStatus(status, null);
    }

    /**
     * 带状态和数据的API返回
     *
     * @param status 状态
     * @param data   数据
     * @return ApiResponse
     */
    public static ApiResponse ofStatus(IStatus status, Object data) {
        return of(status.getCode(), status.getMessage(), data);
    }

    /**
     * 异常API返回
     *
     * @param t   异常对象
     * @param <T> 异常类型
     * @return ApiResponse
     */
    public static <T extends BaseException> ApiResponse ofException(T t) {
        return of(t.getCode(), t.getMessage(), t.getData());
    }
}
