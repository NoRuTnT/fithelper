package com.ssafy.board.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.ssafy.board.security.JwtAuthenticationEntryPoint;
import com.ssafy.board.security.JwtRequestFilter;
import com.ssafy.board.security.JwtUserDetailsService;

@Configuration
@EnableWebSecurity
//@EnableGlobalMethodSecurity(prePostEnabled = true)
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private JwtAuthenticationEntryPoint jwtAuthenticationEntryPoint;

    @Autowired
    private JwtRequestFilter jwtRequestFilter;

    @Autowired
    private JwtUserDetailsService jwtUserDetailsService;

    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

    @Bean
    public DaoAuthenticationProvider authenticationProvider(){
        DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
        authProvider.setUserDetailsService(jwtUserDetailsService);
        authProvider.setPasswordEncoder(passwordEncoder());

        return authProvider;
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.authenticationProvider(authenticationProvider());
    }

    @Bean
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception{
        return super.authenticationManagerBean();
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
	//form형식의 데이터에서 cors오류 방지를 위해서 csrf disable시켜주었다. 
        http.cors()
                .and()
            .csrf()
                .disable()
            .authorizeRequests()
            	.antMatchers("/api-user/login","/api-user/signup" /*, "/api/removeChatroom/**" */).permitAll()
                .antMatchers("/user/{id}", "/user/save").hasAnyAuthority("Member", "Leader", "Admin","CEO")
                .anyRequest()
                    .authenticated()
                .and()
            .exceptionHandling().authenticationEntryPoint(jwtAuthenticationEntryPoint)
                .and()
            .sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
            .formLogin()
                .disable();

        http.addFilterBefore(jwtRequestFilter, UsernamePasswordAuthenticationFilter.class);
    }

    @Override
    public void configure(WebSecurity web) throws Exception {
    //다음의 데이터는 seurity configur제한을 받지 않는다. (정적데이터는 접근가능하도록 설정)
        web.ignoring().antMatchers("/profileUploads/**","/messageUploads/**", "/js/**","/webjars/**","/swagger-ui.html", "/swagger-ui/**",
                "/v2/api-docs", "/v3/api-docs", // Swagger 2.x와 3.x 버전에 따라 다를 수 있음
                "/swagger-resources/**", "/swagger-ui/**","/api-user/**");
    }
}