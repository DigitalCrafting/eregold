package org.digitalcrafting.eregold.config;

import org.digitalcrafting.eregold.authentication.EregoldAuthenticationConverter;
import org.digitalcrafting.eregold.authentication.EregoldAuthenticationFilter;
import org.digitalcrafting.eregold.authentication.EregoldAuthenticationProvider;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import java.util.Arrays;
import java.util.Collections;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(securedEnabled = true, jsr250Enabled = true)
public class SecurityConfig extends WebSecurityConfigurerAdapter {
    private final EregoldAuthenticationFilter eregoldAuthenticationFilter;

    public SecurityConfig(EregoldAuthenticationProvider authenticationProvider,
                          EregoldAuthenticationConverter authenticationConverter) {
        this.eregoldAuthenticationFilter = new EregoldAuthenticationFilter(authenticationProvider, authenticationConverter);
    }

//    @Override
//    public void configure(WebSecurity web) throws Exception {
//        web.ignoring().requestMatchers(
//                new OrRequestMatcher(
//                        new AntPathRequestMatcher("/actuator"),
//                        new AntPathRequestMatcher("/actuator/**"),
//                        new AntPathRequestMatcher("/registration"),
//                        new AntPathRequestMatcher("/registration/**"),
//                        new AntPathRequestMatcher("/login"),
//                        new AntPathRequestMatcher("/login/**")
//                )
//        );
//    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.cors()
                .and()
                .csrf().disable()
                .formLogin().disable()
                .authorizeRequests()
                // Public endpoints
                .antMatchers("/actuator").permitAll()
                .antMatchers("/actuator/**").permitAll()
                .antMatchers("/registration").permitAll()
                .antMatchers("/registration/**").permitAll()
                .antMatchers("/login").permitAll()
                .antMatchers("/login/**").permitAll()
                // The rest
                .anyRequest().authenticated();

        http.addFilterBefore(eregoldAuthenticationFilter, UsernamePasswordAuthenticationFilter.class);
    }

    @Bean
    CorsConfigurationSource corsConfigurationSource() {
        CorsConfiguration configuration = new CorsConfiguration();
        configuration.setAllowedOrigins(Arrays.asList("http://localhost:4200"));
        configuration.setAllowedMethods(Arrays.asList("GET", "POST", "PUT", "DELETE", "OPTIONS"));
        configuration.setAllowedHeaders(Collections.singletonList("*"));
        configuration.setAllowCredentials(false);
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", configuration);
        return source;
    }
}
