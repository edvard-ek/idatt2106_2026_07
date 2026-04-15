package edu.ntnu.idi.idatt.core.user.dto;

public record PupilDTO(Long id,
                String username,
                String email,
                String firstName,
                String lastName,
                Long classroomId,
                String classroomName,
                int xp) {

}
