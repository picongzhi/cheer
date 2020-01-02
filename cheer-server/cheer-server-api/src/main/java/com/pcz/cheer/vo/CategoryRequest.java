package com.pcz.cheer.vo;

import lombok.Data;

import javax.validation.constraints.NotBlank;

/**
 * @author picongzhi
 */
@Data
public class CategoryRequest {
    /**
     * 分类名称
     */
    @NotBlank(message = "分类名称不能为空")
    private String name;

    /**
     * 分类描述
     */
    @NotBlank(message = "描述不能为空")
    private String description;
}
