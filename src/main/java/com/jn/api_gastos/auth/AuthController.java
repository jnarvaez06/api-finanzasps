package com.jn.api_gastos.auth;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.apache.tomcat.util.http.parser.Authorization;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;

    @PostMapping("/login")
    public ResponseEntity<AuthResponse> login(@RequestBody LoginRequest request){
        return ResponseEntity.ok(authService.login(request));
    }

    @PostMapping("/register")
    public ResponseEntity<AuthResponse> register(@Valid @RequestBody RegisterRequest request){
        return ResponseEntity.ok(authService.register(request));
    }

    @GetMapping("/validate")
    public ResponseEntity<?> validate(@RequestHeader(name = "Authorization", required = false) String authHeader) {
        if (authHeader == null || !authHeader.startsWith("Bearer ")) {
            return ResponseEntity.badRequest().body(Map.of("status", false, "message", "Token inválido o ausente"));
        }

        String token = authHeader.substring(7);
        boolean valid = authService.validateToken(token);

        return valid
                ? ResponseEntity.ok(Map.of("status", true))
                : ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                .body(Map.of("status", false, "message", "Token invalido o expirado"));
    }
}
