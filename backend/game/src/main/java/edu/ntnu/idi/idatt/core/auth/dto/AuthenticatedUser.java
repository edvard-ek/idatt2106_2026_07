package edu.ntnu.idi.idatt.core.auth.dto;

public record AuthenticatedUser(
        Long id,
        String username,
        Long schoolId) {
}
