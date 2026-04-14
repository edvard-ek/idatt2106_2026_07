package edu.ntnu.idi.idatt.core.auth.dto;

/**
 * Response body containing issued JWT tokens.
 */
public record AuthResponse(
        String accessToken,
        String refreshToken,
        AuthRole role,
        AuthUserResponse user
) {
}
