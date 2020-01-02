package com.pcz.cheer.util;

import com.pcz.cheer.vo.UserPrincipal;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;

/**
 * @author picongzhi
 */
public class SecurityUtil {
    /**
     * 获取当前登录用户
     * @return
     */
    public static UserPrincipal getCurrentUser() {
        Object principal = SecurityContextHolder.getContext()
                .getAuthentication()
                .getPrincipal();
        if (principal instanceof UserDetails) {
            return (UserPrincipal) principal;
        }

        return null;
    }
}
