package com.pcz.cheer.controller;

import com.pcz.cheer.common.ApiResponse;
import com.pcz.cheer.common.Status;
import com.pcz.cheer.exception.SecurityException;
import com.pcz.cheer.service.UserService;
import com.pcz.cheer.util.JwtUtil;
import com.pcz.cheer.vo.*;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

/**
 * @author picongzhi
 */
@RestController
@RequestMapping("/user")
@Api(tags = "1.0.0-SNAPSHOT", description = "用户管理", value = "用户管理")
public class UserController {
    @Autowired
    private UserService userService;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtUtil jwtUtil;

    @GetMapping
    @ApiOperation(value = "根据token获取用户信息", notes = "根据token获取用户信息")
    public ApiResponse getUser() {
        UserResult userResult = userService.getUserFromSecurityContext();
        if (userResult == null) {
            throw new SecurityException(Status.UNAUTHORIZED);
        }

        return ApiResponse.ofSuccess(userResult);
    }

    @PostMapping("/register")
    @ApiOperation(value = "注册用户", notes = "注册用户")
    public ApiResponse register(@Valid @RequestBody RegisterRequest registerRequest) {
        userService.register(registerRequest);
        return ApiResponse.ofSuccess("注册成功");
    }

    @PostMapping("/login")
    @ApiOperation(value = "用户登录", notes = "用户登录")
    public ApiResponse login(@Valid @RequestBody LoginRequest loginRequest) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword()));
        SecurityContextHolder.getContext().setAuthentication(authentication);
        String jwt = jwtUtil.createJWT(authentication, loginRequest.getRememberMe());

        return ApiResponse.ofSuccess(new JwtResponse(jwt));
    }
}
