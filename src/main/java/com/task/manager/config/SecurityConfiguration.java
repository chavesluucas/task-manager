package com.task.manager.config;

import com.task.manager.filter.FilterAuthentication;
import com.task.manager.filter.FilterLogin;
import com.task.manager.permissions.RoleEnum;
import com.task.manager.service.AuthUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
public class SecurityConfiguration {

    @Autowired
    private AuthUserService authUserService;

    @Autowired
    private AuthenticationConfiguration authenticationConfiguration;

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration) throws Exception {
        return authenticationConfiguration.getAuthenticationManager();
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity httpSecurity) throws Exception {
        httpSecurity.csrf(crsf -> crsf.disable()).authorizeHttpRequests(auth -> {
            auth.requestMatchers("/login").permitAll()
                    .requestMatchers(HttpMethod.GET, "/user").hasAuthority(RoleEnum.USER.toString())
                    .requestMatchers(HttpMethod.POST, "/user").hasAuthority(RoleEnum.ADMIN.toString())
                    .requestMatchers(HttpMethod.POST, "/task-manager").hasAuthority(RoleEnum.ADMIN.toString())
                    .anyRequest()
                    .authenticated();
        });

        httpSecurity.addFilterBefore(new FilterLogin("/login", authenticationConfiguration.getAuthenticationManager()),
                UsernamePasswordAuthenticationFilter.class);
        httpSecurity.addFilterBefore(new FilterAuthentication(), UsernamePasswordAuthenticationFilter.class);

        return httpSecurity.build();
    }
}
