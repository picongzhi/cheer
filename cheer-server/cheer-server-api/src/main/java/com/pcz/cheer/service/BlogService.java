package com.pcz.cheer.service;

import com.pcz.cheer.common.PageResult;
import com.pcz.cheer.model.Blog;
import com.pcz.cheer.vo.BlogRequest;

/**
 * @author picongzhi
 */
public interface BlogService {
    /**
     * 分页获取博客列表
     *
     * @param isMine 是否获取用户自己的
     * @param page   起始页
     * @param size   每页大小
     * @return PageResult<Blog>
     */
    PageResult<Blog> getBlogsByPage(boolean isMine, int page, int size);

    /**
     * 新增博客
     *
     * @param blogRequest BlogRequest
     */
    void addBlog(BlogRequest blogRequest);

    /**
     * 根据id删除博客
     *
     * @param id id
     */
    void deleteBlog(Long id);
}
