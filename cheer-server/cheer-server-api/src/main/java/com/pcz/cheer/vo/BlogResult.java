package com.pcz.cheer.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

/**
 * @author picongzhi
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class BlogResult {
    /**
     * id
     */
    private Long id;

    /**
     * 标题
     */
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
     * 访问数
     */
    private Long visits;

    /**
     * 点赞数
     */
    private Long likes;

    /**
     * 创建时间
     */
    private Date createTime;

    /**
     * 更新时间
     */
    private Date updateTime;
}
