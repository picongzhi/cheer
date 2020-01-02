package com.pcz.cheer.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author picongzhi
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class CategoryResult {
    /**
     * id
     */
    private Long id;

    /**
     * 分类名称
     */
    private String name;

    /**
     * 分类描述
     */
    private String desc;
}
