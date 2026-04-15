package edu.ntnu.idi.idatt.core.user.controller;

import java.util.List;

import edu.ntnu.idi.idatt.core.user.dto.TeacherDTO;
import edu.ntnu.idi.idatt.core.user.service.TeacherService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/teachers")
@RequiredArgsConstructor
@PreAuthorize("hasRole('TEACHER')")
public class TeacherController {

  private final TeacherService teacherService;

  @GetMapping("/{id}")
  public ResponseEntity<TeacherDTO> getById(@PathVariable Long id) {
    return ResponseEntity.ok(teacherService.findById(id));
  }

  @GetMapping("/school/{schoolId}")
  public ResponseEntity<List<TeacherDTO>> getBySchool(@PathVariable Long schoolId) {
    return ResponseEntity.ok(teacherService.findBySchoolId(schoolId));
  }

  @DeleteMapping("/{id}")
  public ResponseEntity<Void> delete(@PathVariable Long id) {
    teacherService.deleteById(id);
    return ResponseEntity.noContent().build();
  }
}
