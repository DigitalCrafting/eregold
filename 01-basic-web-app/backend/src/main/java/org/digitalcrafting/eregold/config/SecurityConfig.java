package org.digitalcrafting.eregold.config;

import org.digitalcrafting.eregold.authentication.EregoldAuthenticationFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.authentication.AnonymousAuthenticationFilter;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private EregoldAuthenticationFilter eregoldAuthenticationFilter;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.formLogin().disable()
                .addFilterBefore(eregoldAuthenticationFilter, AnonymousAuthenticationFilter.class)
                .authorizeRequests()
                .anyRequest().authenticated();
    }
}
