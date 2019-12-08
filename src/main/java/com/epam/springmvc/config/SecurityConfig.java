package com.epam.springmvc.config;

import com.epam.springmvc.security.CustomAuthenticationSuccessHandler;
import com.epam.springmvc.security.UserDetailsServiceImpl;
import com.epam.springmvc.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

@Configuration
@EnableWebSecurity
@ComponentScan("com.epam.springmvc.security")
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private UserService userService;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                .antMatchers("/sign_up", "/login").anonymous()
                .antMatchers("/homePage").hasAnyAuthority("BOOKING_MANAGER", "REGISTERED_USER")
                .antMatchers("/users", "/uploadPage").hasAuthority("BOOKING_MANAGER")
                .and().csrf().disable()
                .formLogin().defaultSuccessUrl("/homePage", true)
                .loginPage("/login")
                .loginProcessingUrl("/login/process")
//                .successHandler(getAuthenticationSuccessHandler()) for the case when we want to manage where to redirect based on the role
                .usernameParameter("email")
                .failureUrl("/login?error=true")
                .and()
                .exceptionHandling()
                .accessDeniedPage("/accessDenied")
                .and()
                .logout().deleteCookies("JSESSIONID")
                .and()
                .rememberMe().key("uniqueAndSecret").userDetailsService(getUserDetailService());;
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
        provider.setPasswordEncoder(passwordEncoder());
        provider.setUserDetailsService(getUserDetailService());
        auth.authenticationProvider(provider);
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public AuthenticationSuccessHandler getAuthenticationSuccessHandler() {
        return new CustomAuthenticationSuccessHandler();
    }

    @Bean
    public UserDetailsService getUserDetailService() {
        return new UserDetailsServiceImpl(userService);
    }
}
