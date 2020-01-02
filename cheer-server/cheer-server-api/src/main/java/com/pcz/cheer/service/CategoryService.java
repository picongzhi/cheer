package com.pcz.cheer.service;

import com.pcz.cheer.vo.CategoryRequest;
import com.pcz.cheer.vo.CategoryResult;

import java.util.List;

/**
 * @author picongzhi
 */
public interface CategoryService {
    /**
     * 获取用户的所有分类
     *
     * @return List<CategoryResult>
     */
    List<CategoryResult> getUserCategories();

    /**
     * 新增分类
     *
     * @param categoryRequest CategoryRequest
     */
    void addCategory(CategoryRequest categoryRequest);
}
