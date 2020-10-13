package de.qsmq.java11introduction.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.stream.Collectors;
import java.util.stream.Stream;


@Configuration
public class WebSecurityConfiguration extends WebSecurityConfigurerAdapter {

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.httpBasic();

        http.authorizeRequests()
                .anyRequest().authenticated();
    }

    // In Memory userdetails manager
    @Bean
    public UserDetailsService userDetailsService() {
        var userDetailsService = new InMemoryUserDetailsManager();


        var fileWithUsers = new ClassPathResource("users.txt");
        String content = "";
        try {
            content =
                    readFileAsString(fileWithUsers.getFile().getAbsolutePath());
        } catch (IOException e) {
            e.printStackTrace();
        }



        var listOfUsersFromFile =
                Stream.of(content.split(","))
                .map(s -> s.split("/"))
                .map(s -> new CustomUserDetail(s[0], s[1], s[2], s[3]))
                .collect(Collectors.toList());


        for (var userFromFile : listOfUsersFromFile) {
            var userDetails = User.withUsername(userFromFile.getUsername())
                    .password(userFromFile.getPassword())
                    .authorities(userFromFile.getAuthorities())
                    .roles(userFromFile.getRoles())
                    .build();

            userDetailsService.createUser(userDetails);
        }

        return  userDetailsService;
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return NoOpPasswordEncoder.getInstance();
    }


    private String readFileAsString(String filePath) {
        byte[] encoded = new byte[0];
        try {
            encoded = Files.readAllBytes(Paths.get(filePath));
        } catch (IOException e) {
            return "";
        }
        return new String(encoded, StandardCharsets.UTF_8);
    }

    private class CustomUserDetail {

        private String username;
        private String password;
        private String authorities;
        private String roles;

        public String getUsername() {
            return username;
        }

        public String getPassword() {
            return password;
        }

        public String getAuthorities() {
            return authorities;
        }

        public String getRoles() {
            return roles;
        }

        public CustomUserDetail(String username, String password, String authorities, String roles) {
            this.username = username;
            this.password = password;
            this.authorities = authorities;
            this.roles = roles;
        }
    }

}
