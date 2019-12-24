package com.pcz.cheer.model;

import lombok.Data;

import javax.persistence.Table;

/**
 * @author picongzhi
 */
@Data
@Table(name = "user_role")
public class UserRole {
    /**
     * 用户id
     */
    private Long userId;

    /**
     * 角色id
     */
    private Long roleId;
}
