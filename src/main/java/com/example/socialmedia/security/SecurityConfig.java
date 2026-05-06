package com.example.socialmedia.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
public class SecurityConfig {

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {

        http
                .csrf(csrf -> csrf.disable())

                //  開啟 CORS
                .cors(cors -> {})

                //  關掉 Spring 預設登入
                .formLogin(form -> form.disable())
                .httpBasic(basic -> basic.disable())

                .authorizeHttpRequests(auth -> auth

                        //  放行 preflight
                        .requestMatchers(HttpMethod.OPTIONS, "/**").permitAll()

                        //  登入註冊放行
                        .requestMatchers("/api/auth/**").permitAll()

                        //  其他都要 JWT
                        .anyRequest().authenticated()
                )

                //  JWT Filter
                .addFilterBefore(
                        new JwtFilter(),
                        UsernamePasswordAuthenticationFilter.class
                );

        return http.build();
    }
}