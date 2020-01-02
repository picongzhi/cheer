package com.pcz.cheer.mapper;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.pcz.cheer.ApplicationTest;
import com.pcz.cheer.model.Blog;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import tk.mybatis.mapper.entity.Example;

import java.util.List;

@Slf4j
public class BlogMapperTest extends ApplicationTest {
    @Autowired
    private BlogMapper blogMapper;

    @Test
    public void pageableSelectTest() {
        int page = 2;
        int size = 3;

        Example example = new Example(Blog.class);
        example.createCriteria().andEqualTo("isPublic", true);

        PageHelper.startPage(page, size);
        List<Blog> blogs = blogMapper.selectByExample(example);

        PageInfo<Blog> pageInfo = new PageInfo<>(blogs);

//        List<Blog> blogs = blogMapper.selectAll();
        log.info("blogs: {}", blogs);
    }
}
