package com.pcz.cheer.controller;

import com.pcz.cheer.entity.User;
import com.pcz.cheer.service.UserService;
import com.pcz.cheer.vo.UserRegisterVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author picongzhi
 */
@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    private UserService userService;

    @GetMapping
    public List<User> listUsers() {
        return userService.getUserList();
    }

    @PostMapping("/register")
    public boolean register(@RequestBody UserRegisterVo userRegisterVo) {
        return userService.register(userRegisterVo);
    }
}
