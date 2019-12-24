package com.pcz.cheer.model;

import lombok.Data;
import tk.mybatis.mapper.annotation.KeySql;

import javax.persistence.Id;
import javax.persistence.Table;

/**
 * @author picongzhi
 */
@Data
@Table(name = "permission")
public class Permission {
    /**
     * 主键
     */
    @Id
    @KeySql(useGeneratedKeys = true)
    private Long id;

    /**
     * 权限名
     */
    private String name;

    /**
     * 接口地址
     */
    private String url;

    /**
     * 权限表达式
     */
    private String permission;

    /**
     * 方法
     */
    private String method;
}
