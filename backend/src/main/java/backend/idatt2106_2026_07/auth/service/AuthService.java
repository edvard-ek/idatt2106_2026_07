package backend.idatt2106_2026_07.auth.service;

import org.springframework.stereotype.Service;

import backend.idatt2106_2026_07.auth.api.dto.LoginRequest;
import backend.idatt2106_2026_07.auth.api.dto.LoginResponse;

@Service
public class AuthService {

    public LoginResponse login(LoginRequest request) {
        // Replace with real user lookup + password hashing validation.
        if ("student@ntnu.no".equalsIgnoreCase(request.email())
                && "password".equals(request.password())) {
            return new LoginResponse("example-jwt-token", "STUDENT");
        }

        throw new IllegalArgumentException("Invalid credentials");
    }
}
