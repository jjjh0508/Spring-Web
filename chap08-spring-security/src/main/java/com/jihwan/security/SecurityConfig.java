package com.jihwan.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.argon2.Argon2PasswordEncoder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    private final AuthenFailHandler authenFailHandler;

    public SecurityConfig(AuthenFailHandler authenFailHandler) {
        this.authenFailHandler = authenFailHandler;
    }

    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

    @Bean
    public SecurityFilterChain configure(HttpSecurity http) throws Exception {

        http.authorizeRequests()
                .mvcMatchers("/*", "/auth/login","/auth/logout").permitAll()
                .antMatchers("/employee/list").hasAnyAuthority("ADMIN", "USER")
                .antMatchers("/employee/admin").hasAnyAuthority("ADMIN")
                .anyRequest().authenticated()
                .and()
                .csrf().disable();

        http.formLogin()
                .loginPage("/auth/login") //해당 url에 post 요청을 하면 로그인 요청 처리
                .defaultSuccessUrl("/")
                .failureHandler(authenFailHandler)
                .usernameParameter("username")
                .passwordParameter("password")
                .and()
                .logout()
                .logoutRequestMatcher(new AntPathRequestMatcher("/auth/logout"))
                .deleteCookies("JSESSIONID")
                .invalidateHttpSession(true)
                .logoutSuccessUrl("/")
                .and()
                .sessionManagement()
                .maximumSessions(1)
                .expiredUrl("/")
                .maxSessionsPreventsLogin(false);

        return http.build();
    }
}
