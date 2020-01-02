package com.pcz.cheer.service.impl;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.date.DateTime;
import com.pcz.cheer.mapper.CategoryMapper;
import com.pcz.cheer.model.Category;
import com.pcz.cheer.service.CategoryService;
import com.pcz.cheer.util.SecurityUtil;
import com.pcz.cheer.vo.CategoryRequest;
import com.pcz.cheer.vo.CategoryResult;
import com.pcz.cheer.vo.UserPrincipal;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.entity.Example;

import java.util.ArrayList;
import java.util.List;

/**
 * @author picongzhi
 */
@Service
public class CategoryServiceImpl implements CategoryService {
    @Autowired
    private CategoryMapper categoryMapper;

    @Override
    public List<CategoryResult> getUserCategories() {
        UserPrincipal userPrincipal = SecurityUtil.getCurrentUser();

        Example example = new Example(Category.class);
        example.orderBy("createTime").asc();
        example.createCriteria().andEqualTo("userId", userPrincipal.getId());

        List<CategoryResult> categoryResults = new ArrayList<>();
        List<Category> categories = categoryMapper.selectByExample(example);
        if (CollUtil.isNotEmpty(categories)) {
            categories.forEach(category -> categoryResults.add(
                    new CategoryResult(category.getId(), category.getName(), category.getDescription())));
        }

        return categoryResults;
    }

    @Override
    public void addCategory(CategoryRequest categoryRequest) {
        UserPrincipal userPrincipal = SecurityUtil.getCurrentUser();

        Category category = Category.builder()
                .name(categoryRequest.getName())
                .description(categoryRequest.getDescription())
                .userId(userPrincipal.getId())
                .createTime(new DateTime())
                .updateTime(new DateTime())
                .build();

        categoryMapper.insert(category);
    }
}
