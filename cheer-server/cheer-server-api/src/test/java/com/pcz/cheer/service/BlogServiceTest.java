package com.pcz.cheer.service;

import com.pcz.cheer.ApplicationTest;
import com.pcz.cheer.common.PageResult;
import com.pcz.cheer.model.Blog;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

@Slf4j
public class BlogServiceTest extends ApplicationTest {
    @Autowired
    private BlogService blogService;

    @Test
    public void getBlogsByPageTest() {
        PageResult<Blog> blogPageResult = blogService.getBlogsByPage(false, 2, 3);
        log.info("page result: {}", blogPageResult);
    }
}
