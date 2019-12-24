package com.pcz.cheer.config;

import com.pcz.cheer.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

/**
 * @author picongzhi
 */
@Configuration
@EnableWebSecurity
@EnableConfigurationProperties(CustomConfig.class)
public class SecurityConfig extends WebSecurityConfigurerAdapter {
    @Autowired
    private CustomConfig customConfig;

    @Autowired
    private AccessDeniedHandler accessDeniedHandler;

    @Autowired
    private UserDetailsService userDetailsService;

    @Autowired
    private JwtAuthenticationFilter jwtAuthenticationFilter;

    @Bean
    BCryptPasswordEncoder encoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.cors()
                .and()
                .csrf().disable()
                .formLogin().disable()
                .httpBasic().disable()
                .authorizeRequests()
                .anyRequest().authenticated()
                .anyRequest().access("@rbacAuthorityService.hasPermission(request, authentication)")
                .and().logout().disable()
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and().exceptionHandling().accessDeniedHandler(accessDeniedHandler);

        // 添加自定义jwt过滤器
        http.addFilterBefore(jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter.class);
    }

    @Override
    public void configure(WebSecurity web) throws Exception {
        WebSecurity webSecurity = web.ignoring().and();
        customConfig.getIgnores().getGet().forEach(url -> webSecurity.ignoring().antMatchers(HttpMethod.GET, url));
        customConfig.getIgnores().getPost().forEach(url -> webSecurity.ignoring().antMatchers(HttpMethod.POST, url));
        customConfig.getIgnores().getDelete().forEach(url -> webSecurity.ignoring().antMatchers(HttpMethod.DELETE, url));
        customConfig.getIgnores().getPut().forEach(url -> webSecurity.ignoring().antMatchers(HttpMethod.PUT, url));
        customConfig.getIgnores().getHead().forEach(url -> webSecurity.ignoring().antMatchers(HttpMethod.HEAD, url));
        customConfig.getIgnores().getPatch().forEach(url -> webSecurity.ignoring().antMatchers(HttpMethod.PATCH, url));
        customConfig.getIgnores().getOptions().forEach(url -> webSecurity.ignoring().antMatchers(HttpMethod.OPTIONS, url));
        customConfig.getIgnores().getTrace().forEach(url -> webSecurity.ignoring().antMatchers(HttpMethod.TRACE, url));
        customConfig.getIgnores().getPattern().forEach(url -> webSecurity.ignoring().antMatchers(url));
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService).passwordEncoder(encoder());
    }
}
