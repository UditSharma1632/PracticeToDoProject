package com.practice.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableMethodSecurity
public class SpringSecurityConfig {

    @Bean
    public static PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

    // This will enable basic authentication in our project which is not recommended

    @Bean
    SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception{

        httpSecurity.csrf().disable()
                .authorizeHttpRequests((authorize) -> {

                    // authorize based on ADMIN role
//                    authorize.requestMatchers(HttpMethod.POST, "/MyToDos/**").hasRole("ADMIN");
//                    authorize.requestMatchers(HttpMethod.PUT, "/MyToDos/**").hasRole("ADMIN");
//                    authorize.requestMatchers(HttpMethod.DELETE, "/MyToDos/**").hasRole("ADMIN");

                    // authorize based on ADMIN and USER
//                    authorize.requestMatchers(HttpMethod.GET, "/MyToDos/*")
//                            .hasAnyRole("ADMIN", "USER");
//                    authorize.requestMatchers(HttpMethod.PATCH, "/MyToDos/*")
//                            .hasAnyRole("ADMIN", "USER");

                    // public access to GET api's
//                    authorize.requestMatchers(HttpMethod.GET, "/MyToDos/**").permitAll();

                    authorize.anyRequest().authenticated();
                }).httpBasic(Customizer.withDefaults());
        return httpSecurity.build();
    }




    // this will create users and assign them roles

    @Bean
    UserDetailsService userDetailsService(){

        UserDetails udit = User.builder()
                .username("udit")
                .password(passwordEncoder().encode("Sharma"))
                .roles("USER")
                .build();

        UserDetails admin = User.builder()
                .username("admin")
                .password(passwordEncoder().encode("admin"))
                .roles("ADMIN")
                .build();

        return new InMemoryUserDetailsManager(udit, admin);
    }
}
