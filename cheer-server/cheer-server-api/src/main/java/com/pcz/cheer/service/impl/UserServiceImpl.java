package com.pcz.cheer.service.impl;

import cn.hutool.core.date.DateTime;
import com.pcz.cheer.entity.User;
import com.pcz.cheer.mapper.UserMapper;
import com.pcz.cheer.service.UserService;
import com.pcz.cheer.vo.UserLoginVo;
import com.pcz.cheer.vo.UserRegisterVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author picongzhi
 */
@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserMapper userMapper;

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
}
