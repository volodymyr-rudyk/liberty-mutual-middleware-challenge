package com.middleware.libertymutualmiddlewarechallenge.service.business.poker;

import com.middleware.libertymutualmiddlewarechallenge.rest.dto.in.pokersession.ConfirmRequestDto;
import com.middleware.libertymutualmiddlewarechallenge.rest.dto.in.pokersession.FullPokerSessionDetailsResponseDto;
import com.middleware.libertymutualmiddlewarechallenge.rest.dto.in.pokersession.JoinPokerSessionRequestDto;
import com.middleware.libertymutualmiddlewarechallenge.rest.dto.in.pokersession.NewPokerSessionRequestDto;
import com.middleware.libertymutualmiddlewarechallenge.rest.dto.out.CollectionContainer;
import com.middleware.libertymutualmiddlewarechallenge.rest.dto.out.pokersession.PokerSessionDetailsResponseDto;

public interface PokerSessionOperations {
    PokerSessionDetailsResponseDto createPokerSession(NewPokerSessionRequestDto request);

    CollectionContainer<PokerSessionDetailsResponseDto> activePokerSessions();

    PokerSessionDetailsResponseDto joinPokerSession(String sessionId, JoinPokerSessionRequestDto request);

    FullPokerSessionDetailsResponseDto pokerSessionDetails(String sessionId);

    void removePokerSessionDetails(String sessionId, ConfirmRequestDto request);
}
