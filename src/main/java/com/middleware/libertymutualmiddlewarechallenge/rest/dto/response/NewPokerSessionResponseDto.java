package com.middleware.libertymutualmiddlewarechallenge.rest.dto.response;

import jakarta.validation.constraints.NotEmpty;

public record NewPokerSessionResponseDto(
        String sessionId
) {
}