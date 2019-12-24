package com.pcz.cheer.config;

import com.pcz.cheer.common.Status;
import com.pcz.cheer.util.ResponseUtil;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.web.access.AccessDeniedHandler;

@Configuration
public class SecurityHandlerConfig {
    @Bean
    public AccessDeniedHandler accessDeniedHandler() {
        return ((httpServletRequest, httpServletResponse, accessDeniedException) ->
                ResponseUtil.renderJson(httpServletResponse, Status.ACCESS_DENIED, null));
    }
}
