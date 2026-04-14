package edu.ntnu.idi.idatt.core.school.dto;

import java.util.List;

import lombok.Data;

@Data
public class SchoolDTO {

  private Long id;
  private String name;
  private String emailSuffix;
  private List<Long> classroomIds;
}
