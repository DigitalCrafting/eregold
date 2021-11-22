package org.digitalcrafting.eregold.config;

import org.digitalcrafting.eregold.authentication.EregoldAuthenticationConverter;
import org.digitalcrafting.eregold.authentication.EregoldAuthenticationFilter;
import org.digitalcrafting.eregold.authentication.EregoldAuthenticationProvider;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.authentication.AnonymousAuthenticationFilter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.security.web.util.matcher.OrRequestMatcher;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.cors.CorsConfigurationSource;

import java.util.Arrays;
import java.util.Collections;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(securedEnabled = true, jsr250Enabled = true)
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Value("${disable-cors}")
    private Boolean disableCors;

    private final EregoldAuthenticationFilter eregoldAuthenticationFilter;

    public SecurityConfig(EregoldAuthenticationProvider authenticationProvider,
                          EregoldAuthenticationConverter authenticationConverter) {
        this.eregoldAuthenticationFilter = new EregoldAuthenticationFilter(authenticationProvider, authenticationConverter);
    }

    @Override
    public void configure(WebSecurity web) throws Exception {
        web.ignoring().requestMatchers(
                new OrRequestMatcher(
                        new AntPathRequestMatcher("/actuator"),
                        new AntPathRequestMatcher("/actuator/**"),
                        new AntPathRequestMatcher("/v1/registration"),
                        new AntPathRequestMatcher("/v1/registration/**")
                )
        );
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        if (this.disableCors) {
            http.cors().configurationSource(corsConfigurationSource());
        }

        http.formLogin().disable()
                .addFilterBefore(eregoldAuthenticationFilter, AnonymousAuthenticationFilter.class)
                .authorizeRequests()
                .anyRequest().authenticated();
    }

    // TODO check if @Bean annotation is required
    @Bean
    CorsConfigurationSource corsConfigurationSource() {
        CorsConfiguration configuration = new CorsConfiguration();
        configuration.setAllowedOrigins(Arrays.asList("*"));
        configuration.setAllowedMethods(Arrays.asList("GET", "POST", "PUT", "DELETE", "OPTIONS"));
        configuration.setAllowedHeaders(Collections.singletonList("*"));
        configuration.setAllowCredentials(false);
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", configuration);
        return source;
    }
}
