package com.pcz.cheer.vo;

import lombok.Data;

/**
 * @author picongzhi
 */
@Data
public class UserLoginVo {
    /**
     * 邮箱
     */
    private String email;

    /**
     * 密码
     */
    private String password;
}
