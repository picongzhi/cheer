package com.pcz.cheer.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import tk.mybatis.mapper.annotation.KeySql;

import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;
import java.util.Date;

/**
 * @author picongzhi
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name = "blog")
public class Blog implements Serializable {
    /**
     * 主键
     */
    @Id
    @KeySql(useGeneratedKeys = true)
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
     * 渲染后的html内容
     */
    private String htmlContent;

    /**
     * 是否公开
     */
    private Boolean isPublic;

    /**
     * 访问量
     */
    private Long visits;

    /**
     * 点赞量
     */
    private Long likes;

    /**
     * 博客所属用户id
     */
    private Long userId;

    /**
     * 博客所属分类id
     */
    private Long categoryId;

    /**
     * 创建时间
     */
    private Date createTime;

    /**
     * 修改时间
     */
    private Date updateTime;
}
