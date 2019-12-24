package com.pcz.cheer.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * @author picongzhi
 */
@ConfigurationProperties(prefix = "jwt.config")
@Data
public class JwtConfig {
    /**
     * jwt加密key
     */
    private String key = "cheer";

    /**
     * jwt过期时间(ms)
     */
    private Long ttl = 600000L;

    /**
     * 开启"记住我"之后的过期时间(ms)
     */
    private Long remember = 604800000L;
}
