package de.qsmq.java11introduction.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
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
        InMemoryUserDetailsManager userDetailsService = new InMemoryUserDetailsManager();


        Resource resource = new ClassPathResource("users.txt");
        String content = "";
        try {
            content =
                    readFileAsString(resource.getFile().getAbsolutePath());
        } catch (IOException e) {
            e.printStackTrace();
        }



        List<CustomUserDetail> userList =
                Stream.of(content.split(","))
                .map(s -> s.split("/"))
                .map(s -> new CustomUserDetail(s[0], s[1], s[2], s[3]))
                .collect(Collectors.toList());


        for (CustomUserDetail cu : userList) {
            UserDetails u = User.withUsername(cu.getUsername())
                    .password(cu.getPassword())
                    .authorities(cu.getAuthorities())
                    .roles(cu.getRoles())
                    .build();

            userDetailsService.createUser(u);
        }

//        UserDetails u1 = User.withUsername("john")
//                .password("1234")
//                .authorities("READ")
//                .roles("ADMIN")
//                .build();
//
//        UserDetails u2 = User.withUsername("jane")
//                .password("1234")
//                .authorities("WRITE")
//                .roles("MANAGER")
//                .build();
//
//        userDetailsService.createUser(u1);
//        userDetailsService.createUser(u2);

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
