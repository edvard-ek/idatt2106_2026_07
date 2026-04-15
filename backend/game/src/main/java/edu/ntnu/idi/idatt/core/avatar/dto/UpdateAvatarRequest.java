package edu.ntnu.idi.idatt.core.avatar.dto;

import jakarta.validation.constraints.NotNull;

/**
 * Represents a request to update an avatar.
 */
public record UpdateAvatarRequest(
    @NotNull(message = "Gender item id is required")
    Long genderId,

    @NotNull(message = "Eye color item id is required")
    Long eyeColorId,

    @NotNull(message = "Skin color item id is required")
    Long skinColorId,

    @NotNull(message = "Hair color item id is required")
    Long hairColorId,

    @NotNull(message = "Hair style item id is required")
    Long hairStyleId,

    @NotNull(message = "Upper body clothing item id is required")
    Long upperBodyClothingId,

    @NotNull(message = "Lower body clothing item id is required")
    Long lowerBodyClothingId,
    
    @NotNull(message = "Hat item id is required")
    Long hatId
) {
}
