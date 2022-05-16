package com.example.amai.config.sercurity;

import com.example.amai.core.admin_user.service.Impl.UserServiceImpl;
import com.example.amai.core.admin_user.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.rememberme.InMemoryTokenRepositoryImpl;
import org.springframework.security.web.authentication.rememberme.PersistentTokenRepository;

@Configuration
/**
 * {@link @EnableWebSecurity} kích hoạt SpringSecurity  trong dự án
 */
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
        //Sử dụng phương thức matches để so sánh pass chưa mã hóa và pass đã mã hóa.
//        bCryptPasswordEncoder.matches("123123","$2a$12$fvKL0pk.qWBp16iCGkhaK.8KnDFVyApXzrW6qHWQE7RnX3XPsuUIC");
        return bCryptPasswordEncoder;
    }


    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().disable()
                .authorizeRequests()
                .antMatchers("/api/home").permitAll()
                .antMatchers("/api/material").permitAll()
                .anyRequest().authenticated()
                .and().sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and();
//                .addFilterBefore(this.jwtRequestFilter, UsernamePasswordAuthenticationFilter.class);
    }
    @Bean
    public PersistentTokenRepository persistentTokenRepository() {
        InMemoryTokenRepositoryImpl memory = new InMemoryTokenRepositoryImpl();
        return memory;
    }
}
