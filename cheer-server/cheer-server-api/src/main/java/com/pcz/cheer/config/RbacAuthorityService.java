package com.pcz.cheer.config;

import com.google.common.collect.ArrayListMultimap;
import com.google.common.collect.Multimap;
import com.pcz.cheer.common.Status;
import com.pcz.cheer.exception.SecurityException;
import com.pcz.cheer.mapper.PermissionMapper;
import com.pcz.cheer.mapper.RoleMapper;
import com.pcz.cheer.model.Permission;
import com.pcz.cheer.model.Role;
import com.pcz.cheer.service.RoleService;
import com.pcz.cheer.service.UserService;
import com.pcz.cheer.vo.UserPrincipal;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.mvc.condition.RequestMethodsRequestCondition;
import org.springframework.web.servlet.mvc.method.RequestMappingInfo;
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerMapping;
import tk.mybatis.mapper.entity.Example;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * @author picongzhi
 */
@Component
public class RbacAuthorityService {
    @Autowired
    private RoleService roleService;

    @Autowired
    private UserService userService;

    @Autowired
    private RequestMappingHandlerMapping mapping;

    public boolean hasPermission(HttpServletRequest request, Authentication authentication) {
        checkRequest(request);

        Object userInfo = authentication.getPrincipal();
        boolean hasPermission = false;
        if (userInfo instanceof UserDetails) {
            UserPrincipal principal = (UserPrincipal) userInfo;
            Long userId = principal.getId();

            List<Role> roles = userService.getRolesByUserId(userId);
            List<Long> roleIds = roles.stream()
                    .map(Role::getId)
                    .collect(Collectors.toList());

            List<Permission> permissions = roleService.getPermissionsByRoleIds(roleIds);
            for (Permission permission : permissions) {
                AntPathRequestMatcher antPathRequestMatcher = new AntPathRequestMatcher(
                        permission.getUrl(), permission.getMethod());
                if (antPathRequestMatcher.matches(request)) {
                    hasPermission = true;
                    break;
                }
            }

            return hasPermission;
        }

        return false;
    }

    private void checkRequest(HttpServletRequest request) {
        String method = request.getMethod();
        Multimap<String, String> urlMapping = allUrlMapping();
        for (String url : urlMapping.keySet()) {
            AntPathRequestMatcher antPathRequestMatcher = new AntPathRequestMatcher(url);
            if (antPathRequestMatcher.matches(request)) {
                if (!urlMapping.get(url).contains(method)) {
                    throw new SecurityException(Status.HTTP_BAD_METHOD);
                } else {
                    return;
                }
            }
        }

        throw new SecurityException(Status.REQUEST_NOT_FOUND);
    }

    /**
     * 获取 url->方法 映射
     *
     * @return Multimap<String, String>
     * 返回格式为{"/url1": ["GET", "POST"], "/url2", ["GET", "DELETE"]}
     */
    private Multimap<String, String> allUrlMapping() {
        Multimap<String, String> urlMapping = ArrayListMultimap.create();

        // 获取RequestMappingInfo -> HandlerMethod 映射
        Map<RequestMappingInfo, HandlerMethod> handlerMethods = mapping.getHandlerMethods();
        handlerMethods.forEach(((requestMappingInfo, handlerMethod) -> {
            Set<String> urls = requestMappingInfo.getPatternsCondition().getPatterns();
            RequestMethodsRequestCondition method = requestMappingInfo.getMethodsCondition();
            urls.forEach(url -> urlMapping.putAll(url, method.getMethods().stream().map(Enum::toString).collect(Collectors.toList())));
        }));

        return urlMapping;
    }
}
