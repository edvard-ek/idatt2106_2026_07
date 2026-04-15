package edu.ntnu.idi.idatt.core.user.mapper;

import edu.ntnu.idi.idatt.core.user.dto.PupilDTO;
import edu.ntnu.idi.idatt.core.user.entity.Pupil;
import org.springframework.stereotype.Component;

@Component
public class PupilMapper {

    public PupilDTO toDTO(Pupil entity) {
        return new PupilDTO(
                entity.getId(),
                entity.getUsername(),
                entity.getEmail(),
                entity.getFirstName(),
                entity.getLastName(),
                entity.getClassroom().getId(),
                entity.getClassroom().getName(),
                entity.getXp());
    }
}
