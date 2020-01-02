package com.pcz.cheer.controller;

import com.pcz.cheer.common.ApiResponse;
import com.pcz.cheer.service.CategoryService;
import com.pcz.cheer.vo.CategoryRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

/**
 * @author picongzhi
 */
@RestController
@RequestMapping("/category")
public class CategoryController {
    @Autowired
    private CategoryService categoryService;

    @GetMapping
    public ApiResponse list() {
        return ApiResponse.ofSuccess(categoryService.getUserCategories());
    }

    @PostMapping
    public ApiResponse add(@Valid @RequestBody CategoryRequest categoryRequest) {
        categoryService.addCategory(categoryRequest);
        return ApiResponse.ofSuccess("操作成功");
    }
}
