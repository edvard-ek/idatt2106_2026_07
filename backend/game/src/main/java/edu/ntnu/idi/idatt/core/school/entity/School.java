package edu.ntnu.idi.idatt.core.school.entity;

import java.util.ArrayList;
import java.util.List;

import edu.ntnu.idi.idatt.core.classroom.entity.Classroom;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Entity
@Table(name = "schools")
@Data
public class School {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @NotBlank(message = "School name is required")
  @Column(nullable = false)
  private String name;

  @OneToMany(mappedBy = "school", cascade = CascadeType.ALL, orphanRemoval = true)
  private List<Classroom> classrooms = new ArrayList<>();
}
