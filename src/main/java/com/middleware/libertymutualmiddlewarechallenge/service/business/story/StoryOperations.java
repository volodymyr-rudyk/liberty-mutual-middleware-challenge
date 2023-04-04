package com.middleware.libertymutualmiddlewarechallenge.service.business.story;

import com.middleware.libertymutualmiddlewarechallenge.rest.controller.VoteDetails;
import com.middleware.libertymutualmiddlewarechallenge.rest.dto.in.story.NewStoryRequestDto;
import com.middleware.libertymutualmiddlewarechallenge.rest.dto.out.CollectionContainer;
import com.middleware.libertymutualmiddlewarechallenge.rest.dto.out.story.FullStoryDetailsResponseDto;
import com.middleware.libertymutualmiddlewarechallenge.rest.dto.out.story.StoryDetailsResponseDto;

public interface StoryOperations {

    StoryDetailsResponseDto createStory(NewStoryRequestDto request);
    StoryDetailsResponseDto voteOnStory(Integer storyId, Integer memberId, Integer value);
    FullStoryDetailsResponseDto storyDetails(Integer storyId);
    StoryDetailsResponseDto startVoting(Integer storyId);
    CollectionContainer<VoteDetails> voteDetails(Integer storyId, Integer memberId);
    StoryDetailsResponseDto finishVoting(Integer storyId);
    void removeStory(Integer storyId);
}
