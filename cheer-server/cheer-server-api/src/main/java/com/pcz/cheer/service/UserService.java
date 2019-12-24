package com.pcz.cheer.service;

import com.pcz.cheer.model.Role;
import com.pcz.cheer.model.User;
import com.pcz.cheer.vo.UserLoginVo;
import com.pcz.cheer.vo.UserRegisterVo;

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
     * 用户注册
     *
     * @param userRegisterVo 用户注册信息
     * @return 是否成功
     */
    boolean register(UserRegisterVo userRegisterVo);

    /**
     * 用户登录
     *
     * @param userLoginVo 用户登录信息
     * @return User
     */
    User login(UserLoginVo userLoginVo);

    /**
     * 根据用户id获取用户角色
     *
     * @param userId 用户id
     * @return List<Role>
     */
    List<Role> getRolesByUserId(Long userId);
}
