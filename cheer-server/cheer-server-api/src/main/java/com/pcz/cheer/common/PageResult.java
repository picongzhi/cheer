package com.pcz.cheer.common;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.List;

/**
 * @author picongzhi
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class PageResult<T> implements Serializable {
    /**
     * 当前页
     */
    private int page;

    /**
     * 每页大小
     */
    private int size;

    /**
     * 总页数
     */
    private int totalPage;

    /**
     * 总数
     */
    private long total;

    /**
     * 当前页数据
     */
    private List<T> rows;

    public static <T> PageResult of(int page, int size, int totalPage, long total, List<T> rows) {
        return new PageResult<>(page, size, totalPage, total, rows);
    }
}
