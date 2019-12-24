package com.pcz.cheer.mapper;

import com.pcz.cheer.ApplicationTest;
import com.pcz.cheer.model.Role;
import com.pcz.cheer.model.User;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

@Slf4j
public class UserMapperTest extends ApplicationTest {
    @Autowired
    private UserMapper userMapper;

    @Test
    public void listUserTest() {
        List<User> userList = userMapper.selectAll();
        log.info("userList: {}", userList);
    }
}
