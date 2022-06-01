package com.example.amai.config.sercurity;

import com.example.amai.core.security.jwt.AuthEntryPointJwt;
import com.example.amai.core.security.jwt.JwtRequestFilter;
import com.example.amai.core.security.service.MyUserDetailsService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    @Override
    @Bean
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }

    @Bean
    public AuthEntryPointJwt entryPointJwt() {
        return new AuthEntryPointJwt();
    }

    @Bean
    public JwtRequestFilter jwtRequestFilter() {
        return new JwtRequestFilter();
    }

    @Autowired
    private MyUserDetailsService myUserDetailService;

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(myUserDetailService);
    }

//    @Bean
//    public PasswordEncoder passwordEncoder() {
//        return new BCryptPasswordEncoder();
//    }

//    @Bean
//    public ModelMapper modelMapper() {
//        return new ModelMapper();
//    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().disable()
                .authorizeRequests()
                .antMatchers(HttpMethod.POST, "/api/login").permitAll()
//                Phân quyền phía admin
                .antMatchers("/api/home").permitAll()
                .antMatchers("/api/admin/food/**").hasAnyRole("MANAGEMENT", "ADMIN")
                .antMatchers("/api/admin/user/**").hasAnyRole("MANAGEMENT", "ADMIN", "CUSTOMER")
                .antMatchers("/api/admin/foodcategory/**").hasAnyRole("MANAGEMENT", "ADMIN")
                .antMatchers("/api/admin/fooddetail/**").hasAnyRole("MANAGEMENT", "ADMIN")
                .antMatchers("/api/admin/material/**").hasAnyRole("MANAGEMENT", "ADMIN")
                .antMatchers("/api/admin/order/**").hasAnyRole("MANAGEMENT", "ADMIN")
                .antMatchers("/api/admin/orderdetail/**").hasAnyRole("MANAGEMENT", "ADMIN")
                .antMatchers("/api/admin/supplier/**").hasAnyRole("MANAGEMENT", "ADMIN")
//              Phân quyền phía user
                .antMatchers("/api/food/**").permitAll()
                .antMatchers("/api/users/**").hasRole("CUSTOMER")
                .antMatchers("/api/sinup/**").permitAll()
                .antMatchers("/api/order/**").hasRole("CUSTOMER")
                .antMatchers("/api/address/**").hasRole("CUSTOMER")
                .antMatchers("/api/pay").hasRole("CUSTOMER")
                .antMatchers("/api/account").hasRole("CUSTOMER")
                .antMatchers("/api/cart/**").hasRole("CUSTOMER")
                .anyRequest().authenticated()
                .and().sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                .addFilterBefore(this.jwtRequestFilter(), UsernamePasswordAuthenticationFilter.class);
    }
}
