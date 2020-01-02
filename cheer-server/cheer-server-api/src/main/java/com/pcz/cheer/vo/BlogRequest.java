package com.pcz.cheer.vo;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

/**
 * @author picongzhi
 */
@Data
public class BlogRequest {
    /**
     * 标题
     */
    @NotBlank(message = "标题不能为空")
    private String title;

    /**
     * 原始内容
     */
    private String rowContent;

    /**
     * html内容
     */
    private String htmlContent;

    /**
     * 分类id
     */
    @NotNull(message = "分类id不能为空")
    private Long categoryId;

    /**
     * 是否对外公开
     */
    private Boolean isPublic;
}
