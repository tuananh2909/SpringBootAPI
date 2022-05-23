package com.ntqsolution.demo.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;

import static com.ntqsolution.demo.config.ApplicationUserRole.*;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
    @Autowired
    private PasswordEncoder passwordEncoder;

    @Bean
    @Override
    public UserDetailsService userDetailsService() {
        UserDetails employeeUser = User.builder()
                .username("employee1")
                .password(passwordEncoder.encode("123123"))
                .roles(EMPLOYEE_MANAGER.name())
                .build();

        UserDetails projectUser = User.builder()
                .username("project1")
                .password(passwordEncoder.encode("123456"))
                .roles(PROJECT_MANAGER.name())
                .build();

        UserDetails adminUser = User.builder()
                .username("admin")
                .password("admin")
                .roles(ADMIN.name())
                .build();

        return new InMemoryUserDetailsManager(
                employeeUser,
                projectUser,
                adminUser
        );
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .csrf().disable()
                .authorizeRequests()
                .antMatchers("/", "/hello").permitAll()
                .antMatchers("/api/employees/**").hasRole(EMPLOYEE_MANAGER.name())
                .antMatchers("/api/projects/**").hasRole(PROJECT_MANAGER.name())
                .anyRequest().authenticated()
                .and()
                .formLogin()
                .permitAll()
                .and()
                .logout()
                .permitAll()
                .and()
                .httpBasic();

    }
}