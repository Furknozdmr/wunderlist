package com.furkanozdemir.config;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.web.cors.CorsConfiguration;

import java.util.Collections;

@Configuration
@RequiredArgsConstructor
public class WebSecurityConfig {

    private static final String[] WHITE_LIST = new String[]{"/auth/sign-up", "/auth/sign-out", "/auth/validate-token",
            "/auth/validate-refresh-token"};

    // private final TokenCacheService tokenCacheService;

    // private final TokenService tokenService;

    @Bean
    public SecurityFilterChain defaultSecurityFilterChain(HttpSecurity http) throws Exception {

        //@formatter:off
        var httpSecurity = http.sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .cors(corsCustomizer -> corsCustomizer.configurationSource(request -> {
                    CorsConfiguration config = new CorsConfiguration();
                    config.setAllowedOrigins(Collections.singletonList("*"));
                    config.setAllowedMethods(Collections.singletonList("*"));
                    config.setAllowCredentials(true);
                    config.setAllowedHeaders(Collections.singletonList("*"));
                    config.setMaxAge(3600L);
                    return config;

                }))
                .csrf(AbstractHttpConfigurer::disable)
                // .addFilterAfter(new JWTGeneratorFilter(tokenCacheService, tokenService), BasicAuthenticationFilter.class)
                .authorizeHttpRequests(r-> r.requestMatchers(WHITE_LIST).permitAll()
                        .requestMatchers("/auth/sign-in").authenticated())
                .formLogin(AbstractHttpConfigurer::disable)
                .httpBasic(c -> c.init(http));
        //@formatter:on

        return httpSecurity.build();

    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();

    }

}
