package edu.ntnu.idi.idatt.core.auth.dto;

public record AuthUserResponse(
        Long id,
        String username,
        String firstName,
        String lastName,
        Long schoolId,
        String schoolName,
        Long classroomId,
        String classroomName
) {
}
