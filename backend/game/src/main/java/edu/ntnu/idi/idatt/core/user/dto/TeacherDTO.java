package edu.ntnu.idi.idatt.core.user.dto;

public record TeacherDTO(Long id,
        String username,
        String email,
        String firstName,
        String lastName,
        Long schoolId,
        String schoolName) {

}
