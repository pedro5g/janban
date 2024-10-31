package com.todo.todo_java.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;


@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Autowired
    private SecurityFilter securityFilter;


    @Autowired 
    private CorsConfigurationSourceImpl configurationSourceImpl;
        
   
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http.csrf(csrf  -> csrf.disable())
        .authorizeHttpRequests(auth -> {
            auth.requestMatchers("/user/register").permitAll()
            .requestMatchers("/user/auth").permitAll().requestMatchers("/**").permitAll();
            auth.anyRequest().authenticated();
        }) 
        .addFilterBefore(securityFilter, BasicAuthenticationFilter.class)
        .cors(cors -> cors.configurationSource(configurationSourceImpl));
        
        
        return http.build();
    }

    @Bean
    PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }



    // @Override
    // public void addCorsMappings(CorsRegistry registry) {
    //     registry.addMapping("/**")
    //         .allowedOrigins("http://localhost:5173")
    //         .allowedHeaders("*")
    //         .allowedMethods("GET", "POST", "PUT", "PATCH","DELETE", "OPTIONS", "HEAD", "TRACE", "CONNECT");
    // }

}

