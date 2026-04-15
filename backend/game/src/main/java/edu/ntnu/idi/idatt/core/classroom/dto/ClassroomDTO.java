package edu.ntnu.idi.idatt.core.classroom.dto;

import java.util.List;

import lombok.Data;

@Data
public class ClassroomDTO {

  private Long id;
  private String name;
  private Long schoolId;
  private String schoolName;
  private Long headTeacherId;
  private String headTeacherName;
  private List<Long> pupilIds;
  private List<Long> teacherIds;
}
