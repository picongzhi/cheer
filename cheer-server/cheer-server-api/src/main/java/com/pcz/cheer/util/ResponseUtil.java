package com.pcz.cheer.util;

import cn.hutool.json.JSONObject;
import cn.hutool.json.JSONUtil;
import com.pcz.cheer.common.ApiResponse;
import com.pcz.cheer.common.BaseException;
import com.pcz.cheer.common.IStatus;
import lombok.extern.slf4j.Slf4j;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author picongzhi
 */
@Slf4j
public class ResponseUtil {
    /**
     * 往response写json
     *
     * @param response http响应
     * @param status   状态
     * @param data     数据
     */
    public static void renderJson(HttpServletResponse response, IStatus status, Object data) {
        try {
            response.setHeader("Access-Control-Allow-Origin", "*");
            response.setHeader("Access-Control-Allow-Methods", "*");
            response.setContentType("application/json;charset=UTF-8");
            response.setStatus(200);
            response.getWriter().write(JSONUtil.toJsonStr(
                    new JSONObject(ApiResponse.ofStatus(status, data), false)));
        } catch (IOException e) {
            log.error("Response写JSON异常, ", e);
        }
    }

    /**
     * 往response写json
     *
     * @param response  http响应
     * @param exception 异常
     */
    public static void renderJson(HttpServletResponse response, BaseException exception) {
        try {
            response.setHeader("Access-Control-Allow-Origin", "*");
            response.setHeader("Access-Control-Allow-Methods", "*");
            response.setContentType("application/json;charset=UTF-8");
            response.setStatus(200);
            response.getWriter().write(JSONUtil.toJsonStr(
                    new JSONObject(ApiResponse.ofException(exception), false)));
        } catch (IOException e) {
            log.error("Response写JSON异常, ", e);
        }
    }
}
