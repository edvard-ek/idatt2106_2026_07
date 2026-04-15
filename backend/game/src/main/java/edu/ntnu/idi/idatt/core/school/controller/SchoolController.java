package edu.ntnu.idi.idatt.core.school.controller;

import java.util.List;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import edu.ntnu.idi.idatt.core.school.dto.SchoolDTO;
import edu.ntnu.idi.idatt.core.school.service.SchoolService;

@RestController
@RequestMapping("/api/schools")
@RequiredArgsConstructor
public class SchoolController {

  private final SchoolService schoolService;

  @GetMapping
  public ResponseEntity<List<SchoolDTO>> getAll() {
    return ResponseEntity.ok(schoolService.findAll());
  }

  @GetMapping("/{id}")
  public ResponseEntity<SchoolDTO> getById(@PathVariable Long id) {
    return ResponseEntity.ok(schoolService.findById(id));
  }

  @DeleteMapping("/{id}")
  @PreAuthorize("hasRole('TEACHER') and @schoolScope.canAccessSchool(authentication, #id)")
  public ResponseEntity<Void> delete(@PathVariable Long id) {
    schoolService.deleteById(id);
    return ResponseEntity.noContent().build();
  }
}
