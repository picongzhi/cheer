package com.pcz.cheer.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author picongzhi
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserResult {
    /**
     * id
     */
    private Long id;

    /**
     * 用户名
     */
    private String username;

    /**
     * 邮箱
     */
    private String email;
}
