package edu.ntnu.idi.idatt.core.user.dto;

import jakarta.validation.constraints.Min;

public record XpUpdateRequest(
        @Min(1) int xpGained) {

}
