package com.pcz.cheer.service.impl;

import cn.hutool.core.date.DateTime;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.pcz.cheer.common.PageResult;
import com.pcz.cheer.common.Status;
import com.pcz.cheer.exception.SecurityException;
import com.pcz.cheer.mapper.BlogMapper;
import com.pcz.cheer.model.Blog;
import com.pcz.cheer.service.BlogService;
import com.pcz.cheer.util.SecurityUtil;
import com.pcz.cheer.vo.BlogRequest;
import com.pcz.cheer.vo.UserPrincipal;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.entity.Example;

import java.util.List;

/**
 * @author picongzhi
 */
@Service
public class BlogServiceImpl implements BlogService {
    @Autowired
    private BlogMapper blogMapper;

    @Override
    public PageResult getBlogsByPage(boolean isMine, int page, int size) {
        Example example = new Example(Blog.class);
        example.orderBy("createTime").desc();

        Example.Criteria criteria = example.createCriteria();
        if (isMine) {
            UserPrincipal userPrincipal = SecurityUtil.getCurrentUser();
            criteria.andEqualTo("userId", userPrincipal.getId());
        } else {
            criteria.andEqualTo("isPublic", true);
        }

        PageHelper.startPage(page, size);
        List<Blog> blogs = blogMapper.selectByExample(example);
        PageInfo<Blog> pageInfo = new PageInfo<>(blogs);

        return PageResult.of(pageInfo.getPageNum(), pageInfo.getPageSize(), pageInfo.getPageSize(), pageInfo.getTotal(), blogs);
    }

    @Override
    public void addBlog(BlogRequest blogRequest) {
        UserPrincipal userPrincipal = SecurityUtil.getCurrentUser();
        Blog.BlogBuilder blogBuilder = Blog.builder()
                .title(blogRequest.getTitle())
                .rowContent(blogRequest.getRowContent())
                .htmlContent(blogRequest.getHtmlContent())
                .userId(userPrincipal.getId())
                .categoryId(blogRequest.getCategoryId())
                .createTime(new DateTime())
                .updateTime(new DateTime());

        if (blogRequest.getIsPublic() != null) {
            blogBuilder.isPublic(blogRequest.getIsPublic());
        }

        blogMapper.insertSelective(blogBuilder.build());
    }

    @Override
    public void deleteBlog(Long id) {
        UserPrincipal userPrincipal = SecurityUtil.getCurrentUser();
        Blog blog = blogMapper.selectByPrimaryKey(id);
        if (!blog.getUserId().equals(userPrincipal.getId())) {
            throw new SecurityException(Status.ACCESS_DENIED);
        }

        blogMapper.deleteByPrimaryKey(id);
    }
}
