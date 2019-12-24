package com.pcz.cheer.common;

/**
 * @author picongzhi
 */
public interface Consts {
    /**
     * 启用
     */
    Integer ENABLE = 1;

    /**
     * 禁用
     */
    Integer DISABLE = 0;

    /**
     * jwt在redis中保存的前缀
     */
    String REDIS_JWT_KEY_PREFIX = "security:jwt:";
}
