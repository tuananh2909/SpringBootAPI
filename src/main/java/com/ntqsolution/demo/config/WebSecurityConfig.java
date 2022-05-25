package com.ntqsolution.demo.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.concurrent.TimeUnit;


@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true, securedEnabled = true, jsr250Enabled = true)
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
    @Autowired
    private PasswordEncoder passwordEncoder;

    @Qualifier("myUserDetailsService")
    @Autowired
    private UserDetailsService userDetailsService;

    @Bean
    public DaoAuthenticationProvider daoAuthenticationProvider() {
        DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
        provider.setPasswordEncoder(passwordEncoder);
        provider.setUserDetailsService(userDetailsService);
        return provider;
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.authenticationProvider(daoAuthenticationProvider());
    }

//    @Bean
//    @Override
//    public UserDetailsService userDetailsService() {
//        UserDetails employeeUser = User.builder()
//                .username("employee1")
//                .password(passwordEncoder.encode("123123"))
////                .roles(EMPLOYEE_MANAGER.name())
//                .authorities(EMPLOYEE_MANAGER.grantedAuthorities())
//                .build();
//
//        UserDetails projectUser = User.builder()
//                .username("project1")
//                .password(passwordEncoder.encode("123456"))
////                .roles(PROJECT_MANAGER.name())
//                .authorities(PROJECT_MANAGER.grantedAuthorities())
//                .build();
//
//        UserDetails adminUser = User.builder()
//                .username("admin1")
//                .password(passwordEncoder.encode("admin123"))
////                .roles(ADMIN.name())
//                .authorities(ADMIN.grantedAuthorities())
//                .build();
//
//        return new InMemoryUserDetailsManager(
//                employeeUser,
//                projectUser,
//                adminUser
//        );
//    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .csrf().disable()
//                .csrf().csrfTokenRepository(CookieCsrfTokenRepository.withHttpOnlyFalse())
//                .and()
                .authorizeRequests()
                //.antMatchers("/", "/hello").permitAll()
//                .antMatchers(HttpMethod.POST, "/api/employees/**").hasAuthority(EMPLOYEE_WRITE.getPermission())
//                .antMatchers(HttpMethod.PUT, "/api/employees/**").hasAuthority(EMPLOYEE_WRITE.getPermission())
//                .antMatchers(HttpMethod.DELETE, "/api/employees/**").hasAuthority(EMPLOYEE_WRITE.getPermission())
                .antMatchers("/api/employees/**", "/employees/**").hasAnyRole("EMPLOYEE_MANAGER", "ADMIN")
                .antMatchers("/api/projects/**", "/projects/**").hasAnyRole("PROJECT_MANAGER", "ADMIN")
                .anyRequest().authenticated()
                .and()
                .formLogin()
                    .defaultSuccessUrl("/home", true)
                    .usernameParameter("username")
                    .passwordParameter("password")
                .and()
                .rememberMe()
                    .tokenValiditySeconds((int) TimeUnit.DAYS.toSeconds(21))
                    .key("somethingVerySecured")
                .and()
                .logout();

    }
}