package edu.ntnu.idi.idatt.core.user.controller;

import java.util.List;

import edu.ntnu.idi.idatt.core.dto.ApiResponse;
import edu.ntnu.idi.idatt.core.user.dto.PupilDTO;
import edu.ntnu.idi.idatt.core.user.dto.XpUpdateRequest;
import edu.ntnu.idi.idatt.core.user.service.PupilService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/pupils")
@RequiredArgsConstructor
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
  public ResponseEntity<ApiResponse<Void>> delete(@PathVariable Long id) {
    pupilService.deleteById(id);
    return ResponseEntity.ok(ApiResponse.ok("Deleted"));
  }

  @PatchMapping("/{id}/xp")
  public ResponseEntity<ApiResponse<Void>> updateXp(@PathVariable Long id,
      @Valid @RequestBody XpUpdateRequest request) {
    pupilService.updateXpById(id, request.xpGained());
    return ResponseEntity.ok(ApiResponse.ok("Pupil xp updated"));
  }
}
