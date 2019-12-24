package com.pcz.cheer.model;

import lombok.Data;

import javax.persistence.Table;

/**
 * @author picongzhi
 */
@Data
@Table(name = "role_permission")
public class RolePermission {
    /**
     * 角色id
     */
    private Long roleId;

    /**
     * 权限id
     */
    private Long permissionId;
}
