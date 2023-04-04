package com.middleware.libertymutualmiddlewarechallenge.rest.dto.in.pokersession;

import jakarta.validation.constraints.NotEmpty;

public record JoinPokerSessionRequestDto(@NotEmpty(message = "nickname") String nickname) {
}