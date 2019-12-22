package com.pcz.cheer.vo;

import lombok.Data;

/**
 * @author picongzhi
 */
@Data
public class UserRegisterVo {
    /**
     * 用户名
     */
    private String username;

    /**
     * 密码
     */
    private String password;

    /**
     * 邮箱
     */
    private String email;
}
