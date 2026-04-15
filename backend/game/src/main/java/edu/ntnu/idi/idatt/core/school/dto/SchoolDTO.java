package edu.ntnu.idi.idatt.core.school.dto;

import java.util.List;

public record SchoolDTO(Long id,
        String name,
        String emailSuffix,
        List<Long> classroomIds) {

}
