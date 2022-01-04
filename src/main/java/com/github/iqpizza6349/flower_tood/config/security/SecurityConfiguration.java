package com.github.iqpizza6349.flower_tood.config.security;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@EnableGlobalMethodSecurity(securedEnabled = true, prePostEnabled = true)
@RequiredArgsConstructor
@Configuration
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

    private final UserDetailsService userDetailsService;

    @Override
    public void configure(WebSecurity web) throws Exception {
        web.ignoring().antMatchers("/v2/api-docs", "/swagger-resources/**",
                "/swagger-ui.html", "/webjars/**", "/swagger/**");
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .csrf().disable().headers().frameOptions().disable()
                .and()
                    .authorizeRequests()
                        .antMatchers("/login", "/sign-up", "/user").permitAll() // 누구나 접근 허용
                        .antMatchers("/").hasRole("USER")   // USER, ADMIN 만 접근 허용
                        .antMatchers("/admin").hasRole("ADMIN") // ADMIN 만 허용
                        .anyRequest().authenticated() // 나머지는 권한이 있기만 하면 접근 가능
                .and()
                    .formLogin() // 로그인에 대한 설정
                        .loginPage("/login") // 로그인 페이지 링크
                        .defaultSuccessUrl("/") // 로그인 성공 시 연결되는 주소
                .and()
                    .logout()// 로그아웃에 대한 설정
                        .logoutSuccessUrl("/")  // 로그아웃 성공 시 연결되는 주소
                        .invalidateHttpSession(true) // 로그아웃 시 저장해 둔 세션 날림
                ;
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService) // 유저 정보는 userDetailService 에서 가져옴
                .passwordEncoder(new BCryptPasswordEncoder()); // 패스워드 인코더는 passwordEncoder (BCrypt)
    }
}
