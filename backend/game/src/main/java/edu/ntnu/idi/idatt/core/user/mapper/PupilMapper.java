package edu.ntnu.idi.idatt.core.user.mapper;

import edu.ntnu.idi.idatt.core.user.dto.PupilDTO;
import edu.ntnu.idi.idatt.core.user.entity.Pupil;
import org.springframework.stereotype.Component;

@Component
public class PupilMapper {

    public PupilDTO toDTO(Pupil entity) {
        PupilDTO dto = new PupilDTO();
        dto.setId(entity.getId());
        dto.setUsername(entity.getUsername());
        dto.setEmail(entity.getEmail());
        dto.setFirstName(entity.getFirstName());
        dto.setLastName(entity.getLastName());
        if (entity.getClassroom() != null) {
            dto.setClassroomId(entity.getClassroom().getId());
            dto.setClassroomName(entity.getClassroom().getName());
        }
        return dto;
    }
}
