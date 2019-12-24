package com.pcz.cheer.vo;

import cn.hutool.core.util.StrUtil;
import com.pcz.cheer.common.Consts;
import com.pcz.cheer.model.Permission;
import com.pcz.cheer.model.Role;
import com.pcz.cheer.model.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

/**
 * @author picongzhi
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserPrincipal implements UserDetails {
    private Long id;

    private String username;

    private String password;

    private String email;

    private Integer status;

    private Date createTime;

    private Date updateTime;

    private List<String> roles;

    private Collection<? extends GrantedAuthority> authorities;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return username;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return Objects.equals(this.status, Consts.ENABLE);
    }

    public static UserPrincipal create(User user, List<Role> roles, List<Permission> permissions) {
        List<String> roleNames = roles.stream()
                .map(Role::getName)
                .collect(Collectors.toList());
        List<GrantedAuthority> authorities = permissions.stream()
                .filter(permission -> StrUtil.isNotBlank(permission.getName()))
                .map(permission -> new SimpleGrantedAuthority(permission.getName()))
                .collect(Collectors.toList());

        return new UserPrincipal(user.getId(), user.getUsername(), user.getPassword(),
                user.getEmail(), user.getStatus(), user.getCreateTime(), user.getUpdateTime(),
                roleNames, authorities);
    }
}
