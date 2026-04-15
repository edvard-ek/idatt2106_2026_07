package edu.ntnu.idi.idatt.core.user.mapper;

import edu.ntnu.idi.idatt.core.user.dto.TeacherDTO;
import edu.ntnu.idi.idatt.core.user.entity.Teacher;
import org.springframework.stereotype.Component;

@Component
public class TeacherMapper {

  public TeacherDTO toDTO(Teacher entity) {
    return new TeacherDTO(
        entity.getId(),
        entity.getUsername(),
        entity.getEmail(),
        entity.getFirstName(),
        entity.getLastName(),
        entity.getSchool().getId(),
        entity.getSchool().getName());
  }
}
