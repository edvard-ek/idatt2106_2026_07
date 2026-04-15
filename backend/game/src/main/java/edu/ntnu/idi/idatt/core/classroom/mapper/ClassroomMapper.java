package edu.ntnu.idi.idatt.core.classroom.mapper;

import java.util.stream.Collectors;

import edu.ntnu.idi.idatt.core.classroom.dto.ClassroomDTO;
import edu.ntnu.idi.idatt.core.classroom.entity.Classroom;
import edu.ntnu.idi.idatt.core.user.entity.Pupil;
import org.springframework.stereotype.Component;

@Component
public class ClassroomMapper {

  public ClassroomDTO toDTO(Classroom entity) {
    ClassroomDTO dto = new ClassroomDTO();
    dto.setId(entity.getId());
    dto.setName(entity.getName());
    if (entity.getSchool() != null) {
      dto.setSchoolId(entity.getSchool().getId());
      dto.setSchoolName(entity.getSchool().getName());
    }
    if (entity.getHeadTeacher() != null) {
      dto.setHeadTeacherId(entity.getHeadTeacher().getId());
      dto.setHeadTeacherName(
          entity.getHeadTeacher().getFirstName() + " " + entity.getHeadTeacher().getLastName());
    }
    if (entity.getPupils() != null) {
      dto.setPupilIds(entity.getPupils().stream().map(Pupil::getId).collect(Collectors.toList()));
    }
    return dto;
  }
}
