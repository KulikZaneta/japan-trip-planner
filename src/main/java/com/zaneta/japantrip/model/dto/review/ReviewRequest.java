package com.zaneta.japantrip.model.dto.review;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;

import java.util.UUID;

public record ReviewRequest(@NotNull(message = "User ID is required") UUID userId,
                            @NotNull(message = "Attraction ID is required") Long attractionId,
                            @NotNull(message = "Rating is required") @Min(value = 1) @Max(value = 5) Long rating,
                            String comment) {
}
