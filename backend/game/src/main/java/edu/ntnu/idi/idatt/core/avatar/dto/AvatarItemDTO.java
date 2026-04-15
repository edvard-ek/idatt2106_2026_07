package edu.ntnu.idi.idatt.core.avatar.dto;

import edu.ntnu.idi.idatt.core.avatar.entity.AvatarSlot;

/**
 * Represents a selectable avatar item.
 */
public record AvatarItemDTO(
    Long id,
    String name,
    AvatarSlot slot
) {
}
