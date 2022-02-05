package com.example.PersonCRUD.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {


    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth
                .inMemoryAuthentication()
                .withUser("superAdmin").password(passwordEncoder().encode("superAdmin")).roles("SUPER_ADMIN").authorities("READ_ALL", "ADD", "EDIT", "DELETE", "GET_ONE")
                .and()
                .withUser("moderator").password(passwordEncoder().encode("moderator")).roles("MODERATOR ").authorities("READ_ALL", "ADD", "EDIT", "GET_ONE")
                .and()
                .withUser("operator").password(passwordEncoder().encode("operator")).roles("OPERATOR").authorities("READ_ALL", "GET_ONE");
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .csrf().disable()
                .authorizeRequests()
                .antMatchers(HttpMethod.DELETE,"/api/*").hasAuthority("DELETE")
                .antMatchers(HttpMethod.GET,"/api/*").hasAnyAuthority("READ_ALL", "GET_ONE")
                .antMatchers(HttpMethod.POST,"/api/*").hasAuthority("ADD")
                .antMatchers(HttpMethod.PUT,"/api/*").hasAuthority("EDIT")
                .anyRequest()
                .authenticated()
                .and()
                .httpBasic();
    }




    @Bean
    PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }
}
