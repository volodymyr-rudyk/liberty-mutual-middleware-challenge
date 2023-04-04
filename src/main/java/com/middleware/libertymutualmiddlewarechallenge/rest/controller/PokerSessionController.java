package com.middleware.libertymutualmiddlewarechallenge.rest.controller;

import com.middleware.libertymutualmiddlewarechallenge.rest.dto.in.pokersession.ConfirmRequestDto;
import com.middleware.libertymutualmiddlewarechallenge.rest.dto.in.pokersession.FullPokerSessionDetailsResponseDto;
import com.middleware.libertymutualmiddlewarechallenge.rest.dto.in.pokersession.JoinPokerSessionRequestDto;
import com.middleware.libertymutualmiddlewarechallenge.rest.dto.in.pokersession.NewPokerSessionRequestDto;
import com.middleware.libertymutualmiddlewarechallenge.rest.dto.out.CollectionContainer;
import com.middleware.libertymutualmiddlewarechallenge.rest.dto.out.pokersession.PokerSessionDetailsResponseDto;
import com.middleware.libertymutualmiddlewarechallenge.service.business.poker.PokerSessionOperations;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@Tag(name = "Poker Sessions", description = "Poker session management API")
@RequiredArgsConstructor
@RequestMapping("/sessions")
@RestController
public class PokerSessionController {

    private final PokerSessionOperations pokerSessionOperations;

    @Operation(description = "Read active poker sessions",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Link to new sessions returned"),
                    @ApiResponse(responseCode = "400", description = "Bad request data provided"),
            })
    @GetMapping
    CollectionContainer<PokerSessionDetailsResponseDto> readActivePokerSessions() {
        return pokerSessionOperations.activePokerSessions();
    }

    @Operation(description = "Create poker session",
            responses = {
                    @ApiResponse(responseCode = "201", description = "Link to new sessions returned"),
                    @ApiResponse(responseCode = "400", description = "Bad request data provided"),
                    @ApiResponse(responseCode = "401", description = "Not Implemented"),
                    @ApiResponse(responseCode = "403", description = "Not Implemented")
            })
    @PostMapping
    PokerSessionDetailsResponseDto createPokerSession(@Valid @RequestBody NewPokerSessionRequestDto request) {
        return pokerSessionOperations.createPokerSession(request);
    }

    @Operation(description = "Join poker session",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Successfully joined poker session"),
                    @ApiResponse(responseCode = "400", description = "Bad request data provided"),
            })
    @PutMapping("{sessionId}/join")
    PokerSessionDetailsResponseDto joinPokerSession(@PathVariable String sessionId,
                                                    @Valid @RequestBody JoinPokerSessionRequestDto request) {
        return pokerSessionOperations.joinPokerSession(sessionId, request);
    }

    @Operation(description = "Get full poker session details",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Details for poker sessions"),
                    @ApiResponse(responseCode = "404", description = "Session not found"),
                    @ApiResponse(responseCode = "403", description = "Forbidden, Not Impl yet)"),
            })
    @GetMapping("/{sessionId}")
    FullPokerSessionDetailsResponseDto getPokerSessionDetails(@PathVariable String sessionId) {
        return pokerSessionOperations.pokerSessionDetails(sessionId);
    }

    @Operation(description = "Remove poker session",
            responses = {
                    @ApiResponse(responseCode = "204", description = "Successfully removed joined poker session")
            })
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping("/{sessionId}")
    void getPokerqSessionDetails(@PathVariable String sessionId, @RequestBody ConfirmRequestDto requestDto) {
        pokerSessionOperations.removePokerSessionDetails(sessionId, requestDto);
    }
}
