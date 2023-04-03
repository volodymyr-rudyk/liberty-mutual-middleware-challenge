package com.middleware.libertymutualmiddlewarechallenge.rest;

import com.middleware.libertymutualmiddlewarechallenge.rest.dto.request.NewPokerSessionRequestDto;
import com.middleware.libertymutualmiddlewarechallenge.rest.dto.response.NewPokerSessionResponseDto;
import com.middleware.libertymutualmiddlewarechallenge.service.business.PokerManagementService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;
import java.util.UUID;

@Tag(name = "Poker Sessions", description = "Poker session management API")
@RequiredArgsConstructor
@RequestMapping("/sessions")
@RestController
public class PokerSessionController {

        private final PokerManagementService pokerManagementService;
    @Operation(description = "Create and join new session",
            responses = {
                    @ApiResponse(responseCode = "201", description = "Link to new sessions returned"),
                    @ApiResponse(responseCode = "400", description = "Bad request data provided"),
                    @ApiResponse(responseCode = "401", description = "Not Implemented"),
                    @ApiResponse(responseCode = "403", description = "Not Implemented")
            })
    @PostMapping
    NewPokerSessionResponseDto object(@Valid @RequestBody NewPokerSessionRequestDto request) {
        return new NewPokerSessionResponseDto(UUID.randomUUID().toString());
    }
}
