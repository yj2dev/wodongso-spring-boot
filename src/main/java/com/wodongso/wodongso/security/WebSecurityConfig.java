package com.wodongso.wodongso.security;

import jdk.swing.interop.SwingInterOpUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import javax.sql.DataSource;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig {

    @Autowired
    private DataSource dataSource;

//    @Bean
//    SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
//        System.out.println("securityFilterChain >> ");
//        return http
//                .csrf(csrf -> csrf.disable())
//                .authorizeRequests(auth -> {
//                    auth.antMatchers("/").permitAll();
//                    auth.antMatchers("/user").hasRole("USER");
//
//                }).httpBasic(Customizer.withDefaults()).build();
//
//    }


    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        return http
                .authorizeHttpRequests((requests) -> requests
                        .antMatchers("/",
                                "/user/register",
                                "/society/list",
                                "/society/detail",
                                "/files/**"
                        ).permitAll()
                        .anyRequest().authenticated()
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
                        .clearAuthentication(true))
                .build();
    }


//    @Bean
//    public UserDetailsService userDetailsService() {
//        return new UserDetailsService
//        return new UserDetailsServiceImpl;
//    }

//
//    @Autowired
//    public void configureGlobal(AuthenticationManagerBuilder auth)
//            throws Exception {
//        System.out.println("ConfigureGlobal >> ");
//        auth.jdbcAuthentication()
//                .dataSource(dataSource)
//                .passwordEncoder(passwordEncoder())
//                .usersByUsernameQuery(
//                        "select id, password, enabled "
//                                + "from user "
//                                + "where id = ?")
//                .authoritiesByUsernameQuery(
//                        "select u.id, r.name "
//                                + "from user_role ur "
//                                + "inner join user u "
//                                + "on ur.user_id = u._id "
//                                + "inner join role r "
//                                + "on ur.role_id = r.id "
//                                + "where u.id = ?");
//    }


    @Bean
    public static PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

}