package com.pcz.cheer.service;

import com.pcz.cheer.model.Role;
import com.pcz.cheer.model.User;
import com.pcz.cheer.vo.RegisterRequest;
import com.pcz.cheer.vo.UserResult;

import java.util.List;

/**
 * @author picongzhi
 */
public interface UserService {
    /**
     * 获取用户列表
     *
     * @return List<User>
     */
    List<User> getUsers();

    /**
     * 根据id获取用户
     *
     * @param id id
     * @return UserResult
     */
    UserResult getUserById(Long id);

    /**
     * 从token获取用户信息
     *
     * @return UserResult
     */
    UserResult getUserFromSecurityContext();

    /**
     * 用户注册
     *
     * @param registerRequest 用户注册信息
     * @return User
     */
    User register(RegisterRequest registerRequest);

    /**
     * 根据用户id获取用户角色
     *
     * @param userId 用户id
     * @return List<Role>
     */
    List<Role> getRolesByUserId(Long userId);
}
