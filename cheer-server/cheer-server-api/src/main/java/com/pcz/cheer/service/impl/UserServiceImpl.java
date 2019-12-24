package com.pcz.cheer.service.impl;

import cn.hutool.core.date.DateTime;
import com.pcz.cheer.mapper.RoleMapper;
import com.pcz.cheer.mapper.UserRoleMapper;
import com.pcz.cheer.model.Permission;
import com.pcz.cheer.model.Role;
import com.pcz.cheer.model.User;
import com.pcz.cheer.mapper.UserMapper;
import com.pcz.cheer.model.UserRole;
import com.pcz.cheer.service.RoleService;
import com.pcz.cheer.service.UserService;
import com.pcz.cheer.vo.UserLoginVo;
import com.pcz.cheer.vo.UserPrincipal;
import com.pcz.cheer.vo.UserRegisterVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.entity.Example;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * @author picongzhi
 */
@Service
public class UserServiceImpl implements UserService, UserDetailsService {
    @Autowired
    private UserMapper userMapper;
    @Autowired
    private UserRoleMapper userRoleMapper;
    @Autowired
    private RoleMapper roleMapper;

    @Autowired
    private RoleService roleService;

    @Override
    public User getUserById(Long id) {
        return userMapper.selectByPrimaryKey(id);
    }

    @Override
    public List<User> getUserList() {
        return userMapper.selectAll();
    }

    @Override
    public boolean register(UserRegisterVo userRegisterVo) {
        User user = User.builder()
                .username(userRegisterVo.getUsername())
                .password(userRegisterVo.getPassword())
                .email(userRegisterVo.getEmail())
                .createTime(new DateTime())
                .updateTime(new DateTime())
                .build();
        return userMapper.insert(user) == 1;
    }

    @Override
    public User login(UserLoginVo userLoginVo) {
        return null;
    }

    @Override
    public List<Role> getRolesByUserId(Long userId) {
        Example userRoleExample = new Example(UserRole.class);
        userRoleExample.createCriteria().andEqualTo("userId", userId);
        List<UserRole> userRoles = userRoleMapper.selectByExample(userRoleExample);
        if (userRoles == null) {
            return null;
        }

        Set<Long> roleIds = userRoles.stream().map(UserRole::getRoleId).collect(Collectors.toSet());
        Example roleExample = new Example(Role.class);
        roleExample.createCriteria().andIn("id", roleIds);

        return roleMapper.selectByExample(roleExample);
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Example userExample = new Example(User.class);
        userExample.createCriteria().orEqualTo("email", username).orEqualTo("username", username);
        User user = userMapper.selectOneByExample(userExample);
        if (user == null) {
            throw new UsernameNotFoundException("未找到用户信息: " + username);
        }

        List<Role> roles = getRolesByUserId(user.getId());
        List<Long> roleIds = roles.stream().map(Role::getId).collect(Collectors.toList());
        List<Permission> permissions = roleService.getPermissionsByRoleIds(roleIds);

        return UserPrincipal.create(user, roles, permissions);
    }
}
