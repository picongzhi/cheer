package com.pcz.cheer.controller;

import com.pcz.cheer.common.ApiResponse;
import com.pcz.cheer.service.BlogService;
import com.pcz.cheer.vo.BlogRequest;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

/**
 * @author picongzhi
 */
@RestController
@RequestMapping("/blog")
@Api(tags = "1.0.0-SNAPSHOT", description = "博客管理", value = "博客管理")
public class BlogController {
    @Autowired
    private BlogService blogService;

    @GetMapping
    public ApiResponse list(@RequestParam(value = "isMine", required = false, defaultValue = "false") Boolean isMine,
                            @RequestParam(value = "page", required = false, defaultValue = "1") Integer page,
                            @RequestParam(value = "size", required = false, defaultValue = "10") Integer size) {
        return ApiResponse.ofSuccess(blogService.getBlogsByPage(isMine, page, size));
    }

    @PostMapping
    public ApiResponse add(@Valid @RequestBody BlogRequest blogRequest) {
        blogService.addBlog(blogRequest);
        return ApiResponse.ofSuccess("操作成功");
    }

    @DeleteMapping("/{id}")
    public ApiResponse delete(@PathVariable Long id) {
        blogService.deleteBlog(id);
        return ApiResponse.ofSuccess("操作成功");
    }
}
