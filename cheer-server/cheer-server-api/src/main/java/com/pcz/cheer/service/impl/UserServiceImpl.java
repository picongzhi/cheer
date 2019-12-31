package com.pcz.cheer.service.impl;

import cn.hutool.core.date.DateTime;
import com.pcz.cheer.common.ApiResponse;
import com.pcz.cheer.mapper.RoleMapper;
import com.pcz.cheer.mapper.UserRoleMapper;
import com.pcz.cheer.model.Permission;
import com.pcz.cheer.model.Role;
import com.pcz.cheer.model.User;
import com.pcz.cheer.mapper.UserMapper;
import com.pcz.cheer.model.UserRole;
import com.pcz.cheer.service.RoleService;
import com.pcz.cheer.service.UserService;
import com.pcz.cheer.vo.UserInfo;
import com.pcz.cheer.vo.UserPrincipal;
import com.pcz.cheer.vo.RegisterRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
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
    public UserInfo getUserById(Long id) {
        User user = userMapper.selectByPrimaryKey(id);
        if (user == null) {
            return null;
        }

        UserInfo userInfo = new UserInfo();
        userInfo.setId(user.getId());
        userInfo.setUsername(user.getUsername());
        userInfo.setEmail(user.getEmail());

        return userInfo;
    }

    @Override
    public UserInfo getUserFromSecurityContext() {
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if (principal instanceof UserDetails) {
            UserPrincipal userPrincipal = (UserPrincipal) principal;
            UserInfo userInfo = new UserInfo();
            userInfo.setId(userPrincipal.getId());
            userInfo.setUsername(userPrincipal.getUsername());
            userInfo.setEmail(userPrincipal.getEmail());

            return userInfo;
        }

        return null;
    }

    @Override
    public List<User> getUserList() {
        return userMapper.selectAll();
    }

    @Override
    public void register(RegisterRequest registerRequest) {
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
