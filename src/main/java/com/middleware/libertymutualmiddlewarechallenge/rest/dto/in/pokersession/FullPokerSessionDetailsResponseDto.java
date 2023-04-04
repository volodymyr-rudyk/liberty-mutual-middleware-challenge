package com.middleware.libertymutualmiddlewarechallenge.rest.dto.in.pokersession;

import java.time.LocalDateTime;
import java.util.Set;

public record FullPokerSessionDetailsResponseDto(String sessionId,
                                                 String title,
                                                 Set<String> members,
                                                 Set<String> stories,
                                                 LocalDateTime createdAt) {
}