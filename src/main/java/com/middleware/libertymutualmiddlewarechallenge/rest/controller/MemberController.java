package com.middleware.libertymutualmiddlewarechallenge.rest.controller;

import com.middleware.libertymutualmiddlewarechallenge.service.business.member.MemberOperations;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@Tag(name = "Member management", description = "Member management API")
@RequiredArgsConstructor
@RequestMapping("/members")
@RestController
public class MemberController {

    private final MemberOperations memberOperations;

//    @Operation(description = "Create new member",
//            responses = {
//                    @ApiResponse(responseCode = "201", description = "New story created"),
//                    @ApiResponse(responseCode = "400", description = "Bad request data provided"),
//            })
//    @PostMapping
//    StoryDetailsResponseDto createStory(@Valid @RequestBody NewStoryRequestDto request) {
//        return storyOperations.createStory(request);
//    }
//
//    @Operation(description = "Story details",
//            responses = {
//                    @ApiResponse(responseCode = "200", description = "Story details"),
//                    @ApiResponse(responseCode = "400", description = "Bad request data provided"),
//            })
//    @GetMapping("/{storyId}")
//    FullStoryDetailsResponseDto storyDetails(@PathVariable String storyId) {
//        return storyOperations.storyDetails(storyId);
//    }
//
//    @Operation(description = "Story details",
//            responses = {
//                    @ApiResponse(responseCode = "200", description = "Story details"),
//                    @ApiResponse(responseCode = "400", description = "Bad request data provided"),
//            })
//    @GetMapping("/{storyId}")
//    FullStoryDetailsResponseDto storyDetails(@PathVariable String storyId) {
//        return storyOperations.storyDetails(storyId);
//    }

}
