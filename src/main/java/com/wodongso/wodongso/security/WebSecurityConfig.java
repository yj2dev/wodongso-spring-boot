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
        return http
                .authorizeHttpRequests((requests) -> requests
                        .antMatchers("/",
                                "/user/register",
                                "/user/logout",
                                "/society/list",
                                "/society/detail",
                                "/files/**",
                                "/css/**"
                        ).permitAll()
                        .antMatchers("/society/status-list")
                        .hasRole("MANAGER")
                        .anyRequest()
                        .authenticated()
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
                        .logoutSuccessUrl("/"))
                .build();
    }

    @Bean
    public static PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

}