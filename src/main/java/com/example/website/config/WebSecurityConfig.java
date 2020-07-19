package com.example.website.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


//@Configuration
////@EnableWebSecurity
//public class WebSecurityConfig {
//
//    @Bean
//    public UserDetailsService userDetailsService() {
//        return new DemoUserDetailsService();
//    }
//
//    @Bean
//    public BCryptPasswordEncoder passwordEncoder() {
//        return new BCryptPasswordEncoder();
//    }
//
//    @Bean
//    public DaoAuthenticationProvider authenticationProvider() {
//        DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
//        authProvider.setUserDetailsService(userDetailsService());
//        authProvider.setPasswordEncoder(passwordEncoder());
//
//        return authProvider;
//    }
//
////    @Override
////    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
////        auth.authenticationProvider(authenticationProvider());
////    }
//
//
////    @Override
////    protected void configure(HttpSecurity http) throws Exception {
////        http.authorizeRequests().
////                antMatchers("/adminRegister2").hasRole("ADMIN").
////                antMatchers("/view").hasRole("USER")
////                .anyRequest().authenticated();
////
////        //h2 magic, TODO: delete me
////        /*http.csrf().disable();
////        http.headers().frameOptions().disable();*/
////    }
//}