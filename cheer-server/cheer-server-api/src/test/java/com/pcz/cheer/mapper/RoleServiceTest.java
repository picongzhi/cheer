package com.pcz.cheer.mapper;

import com.pcz.cheer.ApplicationTest;
import com.pcz.cheer.model.Permission;
import com.pcz.cheer.service.RoleService;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Slf4j
public class RoleServiceTest extends ApplicationTest {
    @Autowired
    private RoleService roleService;

    @Test
    public void test() {
        List<Long> roleIds = new ArrayList<>();
        roleIds.add(1L);
        List<Permission> permissions = roleService.getPermissionsByRoleIds(roleIds);
        log.info("permissions: {}", permissions);
    }
}
