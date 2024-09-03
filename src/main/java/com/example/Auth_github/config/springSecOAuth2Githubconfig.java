package com.example.Auth_github.config;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.oauth2.client.CommonOAuth2Provider;
import org.springframework.security.oauth2.client.registration.ClientRegistration;
import org.springframework.security.oauth2.client.registration.ClientRegistrationRepository;
import org.springframework.security.oauth2.client.registration.InMemoryClientRegistrationRepository;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.web.client.RestTemplate;

@Configuration
public class springSecOAuth2Githubconfig {

    @Bean
    SecurityFilterChain defaultSecurityFilterChain(HttpSecurity http) throws Exception {
        http.authorizeRequests().anyRequest().authenticated()
                .and().oauth2Login();

        return http.build();
    }

    @Bean
    public ClientRegistrationRepository clientRepository() {
        ClientRegistration client = clientRegistration();
        return new InMemoryClientRegistrationRepository(client);
    }

    private ClientRegistration clientRegistration()
    {
        return CommonOAuth2Provider.GITHUB.getBuilder("github").clientId("Ov23lipagj3fYJnKT8By").clientSecret("5553e20bb8d3c7dc8583b1287e46020997184804").build();
    }
    @Bean
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }
}
