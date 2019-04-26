package com.xust.config;

import com.xust.security.AuthProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

/**
 * @author: Luo Daiyang
 * @description:
 * @date Created in 16:55 2019/4/24
 * @modified By:
 */
@EnableWebSecurity
@EnableGlobalMethodSecurity
public class WebSecurityConfig  extends
        WebSecurityConfigurerAdapter {
    /**
     *  
     * HTTP权限控制
     * @author Luo Daiyang
     * @date 2019/4/26 10:32
     * @param [http]
     * @return void
     */
    @Override
    protected void configure(HttpSecurity http) throws Exception {

        // 资源访问权限
        http.authorizeRequests()
                .antMatchers("/admin/login").permitAll() // 管理员登录入口
                .antMatchers("/static/**").permitAll() // 静态资源
                .antMatchers("/user/login").permitAll() // 用户登录入口
                .antMatchers("/admin/**").hasRole("ADMIN")
                .antMatchers("/user/**").hasAnyRole("ADMIN", "USER")
                .antMatchers("/api/user/**").hasAnyRole("ADMIN",
                "USER")
                .and()
                .formLogin()
                .loginProcessingUrl("/login") // 配置角色登录处理入口
                .and();


        http.csrf().disable();
        http.headers().frameOptions().sameOrigin();
    }

    
    /**
     *  
     * 自定义认证策略
     * @author Luo Daiyang
     * @date 2019/4/24 19:34
     * @param [auth]
     * @return void
     */
    @Autowired
    public void configGlobal(AuthenticationManagerBuilder auth) throws Exception {
       auth.authenticationProvider(authProvider()).eraseCredentials(true);
        //内存验证
        //auth.inMemoryAuthentication().withUser("admin").password("admin").roles("ADMIN").and();
    }

    @Bean
    public AuthProvider authProvider() {
        return new AuthProvider();
    }

}
