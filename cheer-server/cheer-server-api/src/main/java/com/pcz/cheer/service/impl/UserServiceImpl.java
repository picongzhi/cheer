package com.pcz.cheer.service.impl;

import cn.hutool.core.date.DateTime;
import cn.hutool.core.util.StrUtil;
import com.pcz.cheer.common.Status;
import com.pcz.cheer.exception.RegisterException;
import com.pcz.cheer.mapper.RoleMapper;
import com.pcz.cheer.mapper.UserRoleMapper;
import com.pcz.cheer.model.Permission;
import com.pcz.cheer.model.Role;
import com.pcz.cheer.model.User;
import com.pcz.cheer.mapper.UserMapper;
import com.pcz.cheer.model.UserRole;
import com.pcz.cheer.service.RoleService;
import com.pcz.cheer.service.UserService;
import com.pcz.cheer.util.SecurityUtil;
import com.pcz.cheer.vo.UserResult;
import com.pcz.cheer.vo.UserPrincipal;
import com.pcz.cheer.vo.RegisterRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
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

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    @Override
    public UserResult getUserById(Long id) {
        User user = userMapper.selectByPrimaryKey(id);
        if (user == null) {
            return null;
        }

        return new UserResult(user.getId(), user.getUsername(), user.getEmail());
    }

    @Override
    public UserResult getUserFromSecurityContext() {
        UserPrincipal userPrincipal = SecurityUtil.getCurrentUser();
        if (userPrincipal == null) {
            return null;
        }

        return new UserResult(userPrincipal.getId(), userPrincipal.getUsername(), userPrincipal.getEmail());
    }

    @Override
    public List<User> getUsers() {
        return userMapper.selectAll();
    }

    @Override
    public User register(RegisterRequest registerRequest) {
        if (StrUtil.compare(registerRequest.getPassword(), registerRequest.getCheckPassword(), false) != 0) {
            throw new RegisterException(Status.PASSWORD_NOT_MATCH);
        }

        User user = User.builder()
                .username(registerRequest.getUsername())
                .password(passwordEncoder.encode(registerRequest.getPassword()))
                .email(registerRequest.getEmail())
                .status(1)
                .createTime(new DateTime())
                .updateTime(new DateTime())
                .build();
        userMapper.insert(user);

        Example roleExample = new Example(Role.class);
        roleExample.createCriteria().andEqualTo("name", "USER");
        Role role = roleMapper.selectOneByExample(roleExample);
        UserRole userRole = UserRole.builder()
                .userId(user.getId())
                .roleId(role.getId())
                .build();
        userRoleMapper.insert(userRole);

        return user;
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
