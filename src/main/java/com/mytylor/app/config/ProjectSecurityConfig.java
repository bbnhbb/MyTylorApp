package com.mytylor.app.config;

import com.mytylor.app.filters.JWTTokenValidatorFilter;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;

import javax.servlet.http.HttpServletRequest;
import java.util.Arrays;
import java.util.Collections;

@Configuration @RequiredArgsConstructor
public class ProjectSecurityConfig extends WebSecurityConfigurerAdapter {

    @Value("${spring.profiles.active}")
    private String env;

    @Autowired
    private JWTTokenValidatorFilter jwtFilter;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.cors().configurationSource(new CorsConfigurationSource() {
            @Override
            public CorsConfiguration getCorsConfiguration(HttpServletRequest request) {
//                Check to see which url to all for cros local or prod
                String crosUrlToAllow = "";
                if (env == "prod") {
                    crosUrlToAllow = "https://bbnhbb.github.io";
                } else {
                    crosUrlToAllow = "http://localhost:3000";
                }

//                configs
                CorsConfiguration config = new CorsConfiguration();
                config.setAllowedOrigins(Collections.singletonList(crosUrlToAllow));
                config.setAllowedMethods(Collections.singletonList("*"));
                config.setAllowCredentials(true);
                config.setAllowedHeaders(Collections.singletonList("*"));
                config.setMaxAge(3600L);
                return config;
            }
        });

        http
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                .csrf().disable()
                .addFilterBefore(jwtFilter, UsernamePasswordAuthenticationFilter.class)
                .authorizeRequests()
                .antMatchers("/login").permitAll()
                .antMatchers("/aboutus").authenticated()
                .antMatchers("/register").permitAll()
                .antMatchers("/saveCusomers").permitAll()
                .antMatchers("/saveDresses").permitAll()
                .antMatchers("/customers").permitAll()
                .antMatchers("/customersForTylor").permitAll()
                .antMatchers("/saveDress").permitAll()
                .antMatchers("/dressesForCustomer").permitAll()
                .and()
                .httpBasic();

    }

    @Bean
    public PasswordEncoder passwordEncoder() {

        return new BCryptPasswordEncoder();
    }



}
