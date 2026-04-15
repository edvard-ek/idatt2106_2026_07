package edu.ntnu.idi.idatt.core.user.controller;

import java.util.List;

import edu.ntnu.idi.idatt.core.user.dto.PupilDTO;
import edu.ntnu.idi.idatt.core.user.service.PupilService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/pupils")
@RequiredArgsConstructor
@PreAuthorize("hasRole('TEACHER')")
public class PupilController {

  private final PupilService pupilService;

  @GetMapping("/{id}")
  public ResponseEntity<PupilDTO> getById(@PathVariable Long id) {
    return ResponseEntity.ok(pupilService.findById(id));
  }

  @GetMapping("/classroom/{classroomId}")
  public ResponseEntity<List<PupilDTO>> getByClassroom(@PathVariable Long classroomId) {
    return ResponseEntity.ok(pupilService.findByClassroomId(classroomId));
  }

  @DeleteMapping("/{id}")
  public ResponseEntity<Void> delete(@PathVariable Long id) {
    pupilService.deleteById(id);
    return ResponseEntity.noContent().build();
  }
}
