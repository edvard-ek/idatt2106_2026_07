package edu.ntnu.idi.idatt.core.school.mapper;

import java.util.stream.Collectors;

import edu.ntnu.idi.idatt.core.classroom.entity.Classroom;
import edu.ntnu.idi.idatt.core.school.dto.SchoolDTO;
import edu.ntnu.idi.idatt.core.school.entity.School;

import org.springframework.stereotype.Component;

@Component
public class SchoolMapper {

  public SchoolDTO toDTO(School entity) {
    SchoolDTO dto = new SchoolDTO();
    dto.setId(entity.getId());
    dto.setName(entity.getName());
    if (entity.getClassrooms() != null) {
      dto.setClassroomIds(
          entity.getClassrooms().stream().map(Classroom::getId).collect(Collectors.toList()));
    }
    return dto;
  }
}
