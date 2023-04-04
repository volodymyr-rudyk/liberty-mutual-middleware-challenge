package com.middleware.libertymutualmiddlewarechallenge.rest.dto.in.story;

public record NewStoryRequestDto(String title, String description, String project, String sessionId) {
}
