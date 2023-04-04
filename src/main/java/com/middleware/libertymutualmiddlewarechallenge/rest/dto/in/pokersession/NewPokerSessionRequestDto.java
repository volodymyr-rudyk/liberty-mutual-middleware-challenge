package com.middleware.libertymutualmiddlewarechallenge.rest.dto.in.pokersession;

import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class NewPokerSessionRequestDto {
    @NotEmpty(message = "title is empty")
    String title;
    @NotEmpty(message = "deskType is empty")
    String deskType;
}