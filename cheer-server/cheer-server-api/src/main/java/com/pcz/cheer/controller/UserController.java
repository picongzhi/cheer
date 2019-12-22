package com.pcz.cheer.controller;

import com.pcz.cheer.entity.User;
import com.pcz.cheer.service.UserService;
import com.pcz.cheer.vo.UserRegisterVo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author picongzhi
 */
@RestController
@RequestMapping("/user")
@Api(tags = "1.0.0-SNAPSHOT", description = "用户管理", value = "用户管理")
public class UserController {
    @Autowired
    private UserService userService;

    @GetMapping
    @ApiOperation(value = "获取用户列表", notes = "获取用户列表")
    @ApiImplicitParams({})
    public List<User> listUsers() {
        return userService.getUserList();
    }

    @PostMapping("/register")
    @ApiOperation(value = "注册用户", notes = "注册用户")
    public boolean register(@RequestBody UserRegisterVo userRegisterVo) {
        return userService.register(userRegisterVo);
    }
}
