package edu.ntnu.idi.idatt.core.school.mapper;

import edu.ntnu.idi.idatt.core.classroom.entity.Classroom;
import edu.ntnu.idi.idatt.core.school.dto.SchoolDTO;
import edu.ntnu.idi.idatt.core.school.entity.School;

import java.util.Collections;
import java.util.List;
import org.springframework.stereotype.Component;

@Component
public class SchoolMapper {

  public SchoolDTO toDTO(School entity) {
    List<Long> classroomIds = entity.getClassrooms() == null
        ? Collections.emptyList()
        : entity.getClassrooms().stream().map(Classroom::getId).toList();

    return new SchoolDTO(
        entity.getId(),
        entity.getName(),
        entity.getEmailSuffix(),
        classroomIds);
  }
}
