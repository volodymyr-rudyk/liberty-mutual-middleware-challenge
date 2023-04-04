package com.middleware.libertymutualmiddlewarechallenge.rest.controller;

import com.middleware.libertymutualmiddlewarechallenge.rest.dto.in.story.NewStoryRequestDto;
import com.middleware.libertymutualmiddlewarechallenge.rest.dto.in.story.VoteRequestDto;
import com.middleware.libertymutualmiddlewarechallenge.rest.dto.out.CollectionContainer;
import com.middleware.libertymutualmiddlewarechallenge.rest.dto.out.story.FullStoryDetailsResponseDto;
import com.middleware.libertymutualmiddlewarechallenge.rest.dto.out.story.StoryDetailsResponseDto;
import com.middleware.libertymutualmiddlewarechallenge.service.business.story.StoryOperations;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@Tag(name = "Story management", description = "Story management API")
@RequiredArgsConstructor
@RequestMapping("/stories")
@RestController
public class StoryController {

    private final StoryOperations storyOperations;

    @Operation(description = "Create new story",
            responses = {
                    @ApiResponse(responseCode = "201", description = "New story created"),
                    @ApiResponse(responseCode = "400", description = "Bad request data provided"),
            })
    @PostMapping
    StoryDetailsResponseDto createStory(@Valid @RequestBody NewStoryRequestDto request) {
        return storyOperations.createStory(request);
    }

    @Operation(description = "Story details",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Story details"),
                    @ApiResponse(responseCode = "400", description = "Bad request data provided"),
            })
    @GetMapping("/{storyId}")
    FullStoryDetailsResponseDto storyDetails(@PathVariable Integer storyId) {
        return storyOperations.storyDetails(storyId);
    }

    @Operation(description = "Story details",
            responses = {
                    @ApiResponse(responseCode = "204", description = "Story details"),
                    @ApiResponse(responseCode = "400", description = "Bad request data provided"),
            })
    @DeleteMapping("/{storyId}")
    void deleteStory(@PathVariable Integer storyId) {
        storyOperations.removeStory(storyId);
    }

    @Operation(description = "Start story voting",
            responses = {
                    @ApiResponse(responseCode = "201", description = "Story vote started"),
                    @ApiResponse(responseCode = "400", description = "Bad request data provided"),
            })
    @PutMapping("/{storyId}/vote/start")
    StoryDetailsResponseDto startStoryVoting(@PathVariable Integer storyId) {
        return storyOperations.startVoting(storyId);
    }

    @Operation(description = "Finish story voting",
            responses = {
                    @ApiResponse(responseCode = "201", description = "Story vote finished"),
                    @ApiResponse(responseCode = "400", description = "Bad request data provided"),
            })
    @PutMapping("/{storyId}/vote/finish")
    StoryDetailsResponseDto finishStoryVoting(@PathVariable Integer storyId) {
        return storyOperations.finishVoting(storyId);
    }

    @Operation(description = "Vote on a story",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Story voted or vote removed"),
                    @ApiResponse(responseCode = "400", description = "Bad request data provided"),
            })
    @PutMapping("/{storyId}/vote")
    StoryDetailsResponseDto voteForStory(@PathVariable Integer storyId,
                                         @RequestBody VoteRequestDto requestDto) {
        return storyOperations.voteOnStory(storyId, requestDto.memberId(), requestDto.value());
    }

    @Operation(description = "Vote details",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Story vote details"),
                    @ApiResponse(responseCode = "400", description = "Bad request data provided"),
            })
    @GetMapping("/{storyId}/vote/{memberId}/details")
    CollectionContainer<VoteDetails> voteForStory(@PathVariable Integer storyId,
                                                  @PathVariable Integer memberId) {
        return storyOperations.voteDetails(storyId, memberId);
    }

}
