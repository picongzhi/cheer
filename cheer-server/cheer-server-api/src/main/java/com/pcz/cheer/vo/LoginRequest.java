package com.pcz.cheer.vo;

import lombok.Data;

import javax.validation.constraints.NotBlank;

/**
 * @author picongzhi
 */
@Data
public class LoginRequest {
    /**
     * 用户名或邮箱
     */
    @NotBlank(message = "用户名不能为空")
    private String username;

    /**
     * 密码
     */
    @NotBlank(message = "密码不能为空")
    private String password;

    /**
     * 记住我
     */
    private Boolean rememberMe;
}
