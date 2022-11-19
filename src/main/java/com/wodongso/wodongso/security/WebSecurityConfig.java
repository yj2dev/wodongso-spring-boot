package com.wodongso.wodongso.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;


@Configuration
@EnableWebSecurity
public class WebSecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {

        http.
                exceptionHandling().
                accessDeniedHandler(new CustomAccessDeniedHandler());

        http
                .authorizeHttpRequests((requests) -> requests
                                .antMatchers("/",
                                        "/user/logout",
                                        "/society/list",
                                        "/user/register",
                                        "/society/detail",
                                        "/files/**",
                                        "/css/**",
                                        "/society/category-board/write"
                                ).permitAll()
                                .antMatchers(
                                        "/user/my-info",
                                        "/user/update-password",
                                        "/user/apply-manager",
                                        "/society/create",
                                        "/society/apply"
                                ).hasAnyRole("USER", "MANAGER", "ADMIN")
                                .antMatchers(
                                        "/society/status-list"
                                )
                                .hasAnyRole("MANAGER", "ADMIN")
                                .antMatchers(
                                        "/user/manager-status-all"
                                )
                                .hasAnyRole("ADMIN")
//                        .anyRequest()
//                        .authenticated()
                )
                .formLogin((form) -> form
                        .loginPage("/user/login")
                        .permitAll()
                        .usernameParameter("id")
                        .passwordParameter("password")
                        .loginProcessingUrl("/user/login")
                        .defaultSuccessUrl("/")
                        .failureUrl("/user/login?fail=true")
                )
                .logout((logout) -> logout.permitAll().logoutRequestMatcher(new AntPathRequestMatcher("/user/logout"))
                        .deleteCookies("sId")
                        .invalidateHttpSession(true)
                        .clearAuthentication(true)
                        .logoutSuccessUrl("/"));


        return http.build();
    }

    @Bean
    public static PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

}