package edu.ntnu.idi.idatt.core.classroom.controller;

import java.util.List;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import edu.ntnu.idi.idatt.core.classroom.dto.ClassroomDTO;
import edu.ntnu.idi.idatt.core.classroom.service.ClassroomService;

@RestController
@RequestMapping("/api/classrooms")
@RequiredArgsConstructor
public class ClassroomController {

  private final ClassroomService classroomService;

  @GetMapping("/{id}")
  public ResponseEntity<ClassroomDTO> getById(@PathVariable Long id) {
    return ResponseEntity.ok(classroomService.findById(id));
  }

  @GetMapping("/school/{schoolId}")
  public ResponseEntity<List<ClassroomDTO>> getBySchool(@PathVariable Long schoolId) {
    return ResponseEntity.ok(classroomService.findBySchoolId(schoolId));
  }

  @DeleteMapping("/{id}")
  public ResponseEntity<Void> delete(@PathVariable Long id) {
    classroomService.deleteById(id);
    return ResponseEntity.noContent().build();
  }
}
