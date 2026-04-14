package edu.ntnu.idi.idatt.core.auth.controller;

import edu.ntnu.idi.idatt.core.auth.dto.AuthResponse;
import edu.ntnu.idi.idatt.core.auth.dto.LoginRequest;
import edu.ntnu.idi.idatt.core.auth.dto.RefreshTokenRequest;
import edu.ntnu.idi.idatt.core.auth.service.AuthService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Exposes authentication endpoints.
 */
@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;

    /**
     * Logs in a user and returns JWT tokens.
     *
     * @param request login request
     * @return issued tokens
     */
    @PostMapping("/login")
    public ResponseEntity<AuthResponse> login(@Valid @RequestBody LoginRequest request) {
        return ResponseEntity.ok(authService.login(request));
    }

    /**
     * Refreshes an access token.
     *
     * @param request refresh token request
     * @return refreshed token response
     */
    @PostMapping("/refresh")
    public ResponseEntity<AuthResponse> refresh(@Valid @RequestBody RefreshTokenRequest request) {
        return ResponseEntity.ok(authService.refresh(request));
    }

    /**
     * Maps invalid requests to a bad request response.
     *
     * @param exception validation exception
     * @return error message
     */
    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<String> handleIllegalArgument(IllegalArgumentException exception) {
        return ResponseEntity.badRequest().body(exception.getMessage());
    }

    /**
     * Maps unfinished login implementation to a temporary response.
     *
     * @param exception unsupported operation
     * @return error message
     */
    @ExceptionHandler(UnsupportedOperationException.class)
    public ResponseEntity<String> handleUnsupportedOperation(UnsupportedOperationException exception) {
        return ResponseEntity.status(501).body(exception.getMessage());
    }
}
