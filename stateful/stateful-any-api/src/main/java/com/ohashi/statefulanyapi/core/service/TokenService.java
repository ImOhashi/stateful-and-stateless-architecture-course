package com.ohashi.statefulanyapi.core.service;

import com.ohashi.statefulanyapi.core.client.TokenClient;
import com.ohashi.statefulanyapi.core.dto.AuthUserResponse;
import com.ohashi.statefulanyapi.infra.exception.AuthenticationException;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import static org.springframework.util.ObjectUtils.isEmpty;

@Slf4j
@Service
@AllArgsConstructor
public class TokenService {

    private final TokenClient tokenClient;

    public void validateToken(String token) {
        try {
            log.info("Sending request for token validation {}", token);
            var response = tokenClient.validateToken(token);
            log.info("Token is valid: {}", response.accessToken());
        } catch (Exception ex) {
            throw new AuthenticationException("Auth error: " + ex.getMessage());
        }
    }

    public AuthUserResponse getAuthenticatedUser(String token) {
        try {
            log.info("Sending request for auth user: {}", token);
            var response = tokenClient.getAuthenticatedUser(token);
            log.info("Auth user found: {} and token {}", response.toString(), token);

            return response;
        } catch (Exception ex) {
            throw new AuthenticationException("Error to get authenticated user: " + ex.getMessage());
        }
    }
}
