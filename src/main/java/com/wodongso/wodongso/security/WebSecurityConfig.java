package com.wodongso.wodongso.security;

import jdk.swing.interop.SwingInterOpUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

import javax.sql.DataSource;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig {

    @Autowired
    private DataSource dataSource;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        System.out.println("securityFilterChain >> ");
        http
                .authorizeHttpRequests((requests) -> requests
                        .antMatchers("/",
                                "/user/register",
                                "/society/list",
                                "/society/detail"
                        ).permitAll()
                        .anyRequest().authenticated()
                )
                .formLogin((form) -> form
                        .loginPage("/user/login")
                        .permitAll()
                )
                .logout((logout) -> logout.permitAll());

        return http.build();
    }


    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth)
            throws Exception {
        System.out.println("ConfigureGlobal >> ");
        auth.jdbcAuthentication()
                .dataSource(dataSource)
                .passwordEncoder(passwordEncoder())
                .usersByUsernameQuery(
                        "select id, password, enabled "
                                + "from user "
                                + "where id = ?")
                .authoritiesByUsernameQuery(
                        "select u.id, r.name "
                                + "from user_role ur "
                                + "inner join user u "
                                + "on ur.user_id = u._id "
                                + "inner join role r "
                                + "on ur.role_id = r.id "
                                + "where u.id = ?");
    }


    @Bean
    public static PasswordEncoder passwordEncoder() {
        System.out.println("passwordEncoder >> ");
        return new BCryptPasswordEncoder();
    }

}