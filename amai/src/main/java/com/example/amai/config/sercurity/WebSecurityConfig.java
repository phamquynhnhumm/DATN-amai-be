package com.example.amai.config.sercurity;

import com.example.amai.core.admin_user.service.Impl.UserServiceImpl;
import com.example.amai.core.admin_user.service.UserService;
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
public class WebSecurityConfig extends  WebSecurityConfigurerAdapter {

    @Override
    @Bean
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }

    @Bean
    public AuthEntryPointJwt entryPointJwt(){
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

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public ModelMapper modelMapper(){return new ModelMapper();}

    @Override
    protected void configure(HttpSecurity http) throws Exception {
//        http.csrf().disable();
//        http.authorizeRequests().anyRequest().permitAll();

//        http.csrf().ignoringAntMatchers("/api/**");
//        http.antMatcher("/api/**").httpBasic().authenticationEntryPoint(entryPointJwt())
//                .and()
//                .authorizeRequests()
//                .antMatchers("/api/**").permitAll()
//                .antMatchers("/api/home").permitAll()
//                .antMatchers(HttpMethod.POST, "/api/login").permitAll()
//                .antMatchers(HttpMethod.POST, "/api/register").permitAll()
//                .antMatchers(HttpMethod.POST, "/api/logout").permitAll()
//                .antMatchers(HttpMethod.GET, "/api/users").hasAnyRole("ADMIN", "MANAGEMENT")
//                .antMatchers(HttpMethod.GET, "/api/orders").hasAnyRole("ADMIN", "MANAGEMENT")
//                .and().sessionManagement()
//                .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
//                .and()
//                .addFilterBefore(jwtRequestFilter(), UsernamePasswordAuthenticationFilter.class);

        http.csrf().disable()
                .authorizeRequests()
                .antMatchers("/api/home").permitAll()
                .antMatchers(HttpMethod.POST, "/api/login").permitAll()
                .antMatchers("/api/food").hasAnyRole("MANAGEMENT","ADMIN","CUSTOMER")
                .antMatchers(HttpMethod.PUT, "/api/users/account/password").hasAnyRole("USER", "ADMIN", "EMPLOYEE")
                .anyRequest().authenticated()
                .and().sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                .addFilterBefore(this.jwtRequestFilter(), UsernamePasswordAuthenticationFilter.class);
    }
}

