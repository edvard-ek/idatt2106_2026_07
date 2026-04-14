package edu.ntnu.idi.idatt.core.user.dto;

import lombok.Data;

@Data
public class TeacherDTO {

  private Long id;
  private String username;
  private String email;
  private String firstName;
  private String lastName;
  private Long schoolId;
  private String schoolName;
}
