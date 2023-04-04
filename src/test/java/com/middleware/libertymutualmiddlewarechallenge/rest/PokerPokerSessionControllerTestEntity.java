package com.middleware.libertymutualmiddlewarechallenge.rest;

import com.middleware.libertymutualmiddlewarechallenge.api.PokerSessionApi;
import com.middleware.libertymutualmiddlewarechallenge.rest.dto.in.pokersession.NewPokerSessionRequestDto;
import com.middleware.libertymutualmiddlewarechallenge.rest.dto.out.pokersession.NewPokerSessionResponseDto;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ProblemDetail;

import java.net.URI;
import java.util.List;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

class PokerPokerSessionControllerTestEntity extends BaseTest {

    @Autowired
    PokerSessionApi pokerSessionApi;

    @Test
    @DisplayName("Bad request on wrong payload provided")
    void badRequestWhenWrongPayloadProvided() {
        var request = new NewPokerSessionRequestDto("", "");
        var response = pokerSessionApi.createNewPokerSession(request, ProblemDetail.class);
        assertAll(
                () -> assertEquals(response.getStatusCode(), HttpStatus.BAD_REQUEST),
                () -> assertEquals(response.getBody().getInstance(), URI.create("/sessions")),
                () -> assertEquals(response.getBody().getDetail(), List.of("deskType is empty", "title is empty").toString())
        );
    }

    @Test
    void okWhenCorrectDataProvided() {
        var request = new NewPokerSessionRequestDto("New Session", "Junior");
        var response = pokerSessionApi.createNewPokerSession(request, NewPokerSessionResponseDto.class);
        assertAll(
                () -> assertEquals(response.getStatusCode(), HttpStatus.OK),
                () -> assertDoesNotThrow(() -> UUID.fromString(response.getBody().sessionId()))
        );
    }
}