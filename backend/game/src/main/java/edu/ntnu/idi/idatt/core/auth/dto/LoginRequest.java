package edu.ntnu.idi.idatt.core.auth.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

/**
 * Request body for user login.
 */
public record LoginRequest(
        @NotBlank(message = "Email is required")
        @Email(message = "Email must be valid")
        String email,

        @NotNull(message = "School id is required")
        Long schoolId,
        
        @NotBlank(message = "Password is required")
        String password
) {
}
