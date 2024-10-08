package com.ems.EmployeeService.controller;

import com.ems.EmployeeService.security.AuthenticationResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.client.OAuth2AuthorizedClient;
import org.springframework.security.oauth2.client.annotation.RegisteredOAuth2AuthorizedClient;
import org.springframework.security.oauth2.core.oidc.user.OidcUser;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.security.core.GrantedAuthority;


import java.util.stream.Collectors;

@RestController
@RequestMapping("/authenticate")
public class AuthenticationController {

    @GetMapping("/login")
    public ResponseEntity<AuthenticationResponse> login(
            @AuthenticationPrincipal OidcUser oidcUser,
            @RegisteredOAuth2AuthorizedClient("okta") OAuth2AuthorizedClient client) {

        String refreshToken = client.getRefreshToken() != null ? client.getRefreshToken().getTokenValue() : null;
        Long expiresTokenAt = client.getAccessToken().getExpiresAt() != null
                ? client.getAccessToken().getExpiresAt().getEpochSecond()
                : null;

        AuthenticationResponse authenticationResponse
                = AuthenticationResponse.builder()
                .userId(oidcUser.getEmail())
                .accessToken(client.getAccessToken().getTokenValue())
                .refreshToken(refreshToken)
                .expiresTokenAt(expiresTokenAt)
                .authorityList(oidcUser.getAuthorities()
                        .stream()
                        .map(GrantedAuthority::getAuthority)
                        .collect(Collectors.toList()))
                .build();

        return new ResponseEntity<>(authenticationResponse, HttpStatus.OK);
    }
}
