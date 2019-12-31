package com.pcz.cheer.service;

import com.pcz.cheer.model.Role;
import com.pcz.cheer.model.User;
import com.pcz.cheer.vo.RegisterRequest;

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
    List<User> getUserList();

    /**
     * 根据id获取用户
     *
     * @param id id
     * @return User
     */
    User getUserById(Long id);

    /**
     * 用户注册
     *
     * @param registerRequest 用户注册信息
     * @return 是否成功
     */
    boolean register(RegisterRequest registerRequest);

    /**
     * 根据用户id获取用户角色
     *
     * @param userId 用户id
     * @return List<Role>
     */
    List<Role> getRolesByUserId(Long userId);
}
