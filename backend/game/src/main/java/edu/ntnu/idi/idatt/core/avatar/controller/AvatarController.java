package edu.ntnu.idi.idatt.core.avatar.controller;

import edu.ntnu.idi.idatt.core.auth.dto.AuthenticatedUser;
import edu.ntnu.idi.idatt.core.avatar.dto.AvatarDTO;
import edu.ntnu.idi.idatt.core.avatar.dto.AvatarItemDTO;
import edu.ntnu.idi.idatt.core.avatar.dto.UpdateAvatarRequest;
import edu.ntnu.idi.idatt.core.avatar.entity.AvatarSlot;
import edu.ntnu.idi.idatt.core.avatar.service.AvatarService;
import jakarta.validation.Valid;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Exposes avatar endpoints.
 */
@RestController
@RequestMapping("/api/avatars")
@RequiredArgsConstructor
public class AvatarController {

  private final AvatarService avatarService;

  /**
   * Gets the avatar for the authenticated user.
   *
   * @return the avatar configuration
   */
  @GetMapping("/me")
  public ResponseEntity<AvatarDTO> getMyAvatar(@AuthenticationPrincipal AuthenticatedUser user) {
    return ResponseEntity.ok(avatarService.findByUserId(user.id()));
  }

  /**
   * Gets all available avatar items.
   *
   * @return the available avatar items
   */
  @GetMapping("/items")
  public ResponseEntity<List<AvatarItemDTO>> getAllItems() {
    return ResponseEntity.ok(avatarService.findAllItems());
  }

  /**
   * Gets avatar items for a slot.
   *
   * @param slot the avatar slot
   * @return the avatar items for the slot
   */
  @GetMapping("/items/slot/{slot}")
  public ResponseEntity<List<AvatarItemDTO>> getItemsBySlot(@PathVariable AvatarSlot slot) {
    return ResponseEntity.ok(avatarService.findItemsBySlot(slot));
  }

  /**
   * Saves or updates the avatar for the authenticated user.
   *
   * @param request the avatar update request
   * @return the saved avatar configuration
   */
  @PutMapping("/me")
  public ResponseEntity<AvatarDTO> saveOrUpdateMyAvatar(@AuthenticationPrincipal AuthenticatedUser user,
      @Valid @RequestBody UpdateAvatarRequest request) {
    return ResponseEntity.ok(avatarService.saveOrUpdate(user.id(), request));
  }
}
