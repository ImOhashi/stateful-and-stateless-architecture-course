package com.ohashi.statelessauthapi.core.controller;

import com.ohashi.statelessauthapi.core.dto.AuthRequest;
import com.ohashi.statelessauthapi.core.dto.TokenDTO;
import com.ohashi.statelessauthapi.core.service.AuthService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@AllArgsConstructor
@RequestMapping("api/auth")
public class AuthController {

    private final AuthService authService;

    @PostMapping("login")
    public TokenDTO login(@RequestBody  AuthRequest request) {
        return authService.login(request);
    }

    @PostMapping("token/validate")
    public TokenDTO validate(@RequestHeader String accessToken) {
        return authService.validateToken(accessToken);
    }
}
