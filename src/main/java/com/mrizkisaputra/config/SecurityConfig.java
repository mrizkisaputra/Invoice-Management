package com.mrizkisaputra.config;

import com.mrizkisaputra.helper.SqlHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.JdbcUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

import javax.sql.DataSource;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    private final DataSource datasource;

    @Autowired
    public SecurityConfig(DataSource datasource) {
        this.datasource = datasource;
    }

    @Bean
    public UserDetailsService userDetailsService() {
        JdbcUserDetailsManager userDetailsManager = new JdbcUserDetailsManager(datasource);
        userDetailsManager.setUsersByUsernameQuery(SqlHelper.SQL_QUERY_LOGIN);
        userDetailsManager.setAuthoritiesByUsernameQuery(SqlHelper.SQL_QUERY_PERMISSION);
        return userDetailsManager;
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .authorizeHttpRequests(configurer -> {
                    configurer
                            .requestMatchers("/register/**").permitAll() // izinkan akses view registrasi
                            .requestMatchers("/password/**").permitAll()
                            .requestMatchers("/login").permitAll()
                            .requestMatchers("/assets/**").permitAll() // izinkan akses ke static assets
                            .requestMatchers("/", "/home").hasAnyAuthority("VIEW_TRANSAKSI", "EDIT_TRANSAKSI") // izinkan akses hanya yang memiliki permission
                            .anyRequest().authenticated(); // semua request lain harus login
                })
                .formLogin(Customizer.withDefaults())
                .logout(Customizer.withDefaults());

        return http.build();
    }

}
