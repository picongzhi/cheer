package com.pcz.cheer.service;

import com.pcz.cheer.model.Permission;

import java.util.List;

/**
 * @author picongzhi
 */
public interface RoleService {
    /**
     * 根据角色id集合获取权限
     * @param roleIds 角色id集合
     * @return List<Permission>
     */
    List<Permission> getPermissionsByRoleIds(List<Long> roleIds);
}
