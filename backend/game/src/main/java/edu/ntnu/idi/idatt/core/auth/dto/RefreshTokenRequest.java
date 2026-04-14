package edu.ntnu.idi.idatt.core.auth.dto;

import jakarta.validation.constraints.NotBlank;

/**
 * Request body for refreshing an access token.
 */
public record RefreshTokenRequest(
        @NotBlank(message = "Refresh token is required")
        String refreshToken
) {
}
