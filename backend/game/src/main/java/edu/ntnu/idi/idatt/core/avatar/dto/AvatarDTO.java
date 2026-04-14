package edu.ntnu.idi.idatt.core.avatar.dto;

/**
 * Represents a user's avatar configuration.
 */
public record AvatarDTO(
    Long id,
    Long userId,
    Long genderItemId,
    Long eyeColorItemId,
    Long skinColorItemId,
    Long hairColorItemId,
    Long hairStyleItemId,
    Long upperBodyClothingItemId,
    Long lowerBodyClothingItemId,
    Long hatItemId
) {
}
