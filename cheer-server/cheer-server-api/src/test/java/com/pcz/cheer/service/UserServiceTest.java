package com.pcz.cheer.service;

import com.pcz.cheer.ApplicationTest;
import com.pcz.cheer.model.Role;
import com.pcz.cheer.model.User;
import com.pcz.cheer.vo.RegisterRequest;
import com.pcz.cheer.vo.UserResult;
import lombok.extern.slf4j.Slf4j;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.util.List;

@Slf4j
public class UserServiceTest extends ApplicationTest {
    @Autowired
    private UserService userService;
    @Autowired
    private BCryptPasswordEncoder encoder;

    @Test
    public void getUsersTest() {
        List<User> users = userService.getUsers();
        log.info("users: {}", users);
        Assert.assertNotNull(users);
    }

    @Test
    public void getUserByIdTest() {
        Long id = 1L;
        UserResult userResult = userService.getUserById(id);
        log.info("userResult: {}", userResult);
        Assert.assertNotNull(userResult);
        Assert.assertEquals(userResult.getId(), id);
    }

    @Test
    public void getUserFromSecurityContextTest() {
        UserResult userResult = userService.getUserFromSecurityContext();
        log.info("userResult: {}", userResult);
        Assert.assertNull(userResult);
    }

    @Test
    public void registerTest() {
        RegisterRequest registerRequest = new RegisterRequest();
        registerRequest.setUsername("username");
        registerRequest.setPassword("password");
        registerRequest.setCheckPassword("password");
        registerRequest.setEmail("username.email.com");

        User user = userService.register(registerRequest);
        log.info("user: {}", user);
        Assert.assertNotNull(user);
    }

    @Test
    public void getRolesByUserIdTest() {
        Long id = 1L;
        List<Role> roles = userService.getRolesByUserId(id);
        log.info("roles: {}", roles);
    }

    @Test
    public void encoderTest() {
        String password = "123456";
        String encodedPassword = encoder.encode(password);
        log.info("encoded password: {}", encodedPassword);
    }
}
