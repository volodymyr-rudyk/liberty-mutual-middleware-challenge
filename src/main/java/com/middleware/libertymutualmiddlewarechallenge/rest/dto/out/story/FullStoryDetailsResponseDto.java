package com.middleware.libertymutualmiddlewarechallenge.rest.dto.out.story;

public record FullStoryDetailsResponseDto(String id, String title, String status, String description, String project, String sessionId, Integer votes) {
}
