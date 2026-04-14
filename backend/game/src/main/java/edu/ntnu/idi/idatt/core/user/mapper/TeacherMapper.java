package edu.ntnu.idi.idatt.core.user.mapper;

import edu.ntnu.idi.idatt.core.user.dto.TeacherDTO;
import edu.ntnu.idi.idatt.core.user.entity.Teacher;
import org.springframework.stereotype.Component;

@Component
public class TeacherMapper {

  public TeacherDTO toDTO(Teacher entity) {
    TeacherDTO dto = new TeacherDTO();
    dto.setId(entity.getId());
    dto.setUsername(entity.getUsername());
    dto.setEmail(entity.getEmail());
    dto.setFirstName(entity.getFirstName());
    dto.setLastName(entity.getLastName());
    if (entity.getSchool() != null) {
      dto.setSchoolId(entity.getSchool().getId());
      dto.setSchoolName(entity.getSchool().getName());
    }
    return dto;
  }
}
