package com.pcz.cheer.service.impl;

import com.pcz.cheer.mapper.PermissionMapper;
import com.pcz.cheer.mapper.RolePermissionMapper;
import com.pcz.cheer.model.Permission;
import com.pcz.cheer.model.RolePermission;
import com.pcz.cheer.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.entity.Example;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;


/**
 * @author picongzhi
 */
@Service
public class RoleServiceImpl implements RoleService {
    @Autowired
    private RolePermissionMapper rolePermissionMapper;
    @Autowired
    private PermissionMapper permissionMapper;

    @Override
    public List<Permission> getPermissionsByRoleIds(List<Long> roleIds) {
        if (roleIds == null || roleIds.size() == 0) {
            return null;
        }

        Example rolePermissionExample = new Example(RolePermission.class);
        rolePermissionExample.createCriteria().andIn("roleId", roleIds);
        List<RolePermission> rolePermissions = rolePermissionMapper.selectByExample(rolePermissionExample);
        if (rolePermissions == null || rolePermissions.size() == 0) {
            return null;
        }

        Set<Long> permissionIds = rolePermissions.stream()
                .map(RolePermission::getPermissionId)
                .collect(Collectors.toSet());
        Example permissionExample = new Example(Permission.class);
        permissionExample.createCriteria().andIn("id", permissionIds);

        return permissionMapper.selectByExample(permissionExample);
    }
}
