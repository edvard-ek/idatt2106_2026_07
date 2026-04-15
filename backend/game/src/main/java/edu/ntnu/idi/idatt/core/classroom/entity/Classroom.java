package edu.ntnu.idi.idatt.core.classroom.entity;

import java.util.ArrayList;
import java.util.List;

import edu.ntnu.idi.idatt.core.school.entity.School;
import edu.ntnu.idi.idatt.core.user.entity.Pupil;
import edu.ntnu.idi.idatt.core.user.entity.Teacher;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.JoinTable;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Entity
@Table(name = "classrooms")
@Data
public class Classroom {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @NotBlank(message = "Classroom name is required")
  @Column(nullable = false, length = 100)
  private String name;

  @OneToOne
  @JoinColumn(name = "head_teacher_id")
  private Teacher headTeacher;

  @ManyToMany
  @JoinTable(
      name = "classroom_teachers",
      joinColumns = @JoinColumn(name = "classroom_id"),
      inverseJoinColumns = @JoinColumn(name = "teacher_id")
  )
  private List<Teacher> teachers = new ArrayList<>();

  @OneToMany(mappedBy = "classroom", cascade = CascadeType.ALL, orphanRemoval = true)
  private List<Pupil> pupils = new ArrayList<>();

  @ManyToOne
  @JoinColumn(name = "school_id")
  private School school;
}
