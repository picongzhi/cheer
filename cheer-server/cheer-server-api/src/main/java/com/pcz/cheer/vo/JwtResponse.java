package com.pcz.cheer.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author picongzhi
 */
@Data
@AllArgsConstructor
public class JwtResponse {
    /**
     * token
     */
    private String token;

    /**
     * 类型
     */
    private String type = "Bearer";

    public JwtResponse(String token) {
        this.token = token;
    }
}
