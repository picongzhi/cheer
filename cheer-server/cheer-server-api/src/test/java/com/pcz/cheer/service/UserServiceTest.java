package com.pcz.cheer.service;

import com.pcz.cheer.ApplicationTest;
import com.pcz.cheer.model.Role;
import lombok.extern.slf4j.Slf4j;
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
    public void getRolesByUserIdTest() {
        List<Role> roles = userService.getRolesByUserId(1L);
        log.info("roles: {}", roles);
    }

    @Test
    public void encoderTest() {
        String password = "123456";
        String encodedPassword = encoder.encode(password);
        log.info("encoded password: {}", encodedPassword);
    }
}
