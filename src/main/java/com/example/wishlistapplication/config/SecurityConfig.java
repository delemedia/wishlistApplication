package com.example.wishlistapplication.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
@Configuration
@EnableWebSecurity
@EnableMethodSecurity
public class SecurityConfig {
    @Bean
    CustomUserDetailsService customUserDetailsService() {
        return new CustomUserDetailsService();
    }
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                .antMatchers("/",
                        "/registerPage",
                        "/css/**")
                .permitAll()
                .and()
                .authorizeRequests()
                .antMatchers("/showWishListsPage",
                        "/indexOfWishLists",
                        "/showWishlistWishes",
                        "/createWishList",
                        "/updateWishList**",
                        "/deleteWishList**",
                        "/showWishesPage",
                        "/createWish",
                        "/updateWishData**",
                        "/deleteWishData**")
                .hasRole("USER")
                .anyRequest()
                .authenticated()
                .and()
                .formLogin()
                    .defaultSuccessUrl("/showWishListsPage", true)
                .and()
                .logout()
                    .logoutSuccessUrl("/")
                .and()
                .httpBasic();
        return http.build();
    }
}
