package com.middleware.libertymutualmiddlewarechallenge.service.business;

import com.middleware.libertymutualmiddlewarechallenge.exception.RecordNotFound;
import com.middleware.libertymutualmiddlewarechallenge.jpa.model.*;
import com.middleware.libertymutualmiddlewarechallenge.jpa.repository.MemberRepository;
import com.middleware.libertymutualmiddlewarechallenge.jpa.repository.PokerSessionRepository;
import com.middleware.libertymutualmiddlewarechallenge.jpa.repository.StoryRepository;
import com.middleware.libertymutualmiddlewarechallenge.jpa.repository.VoteRepository;
import com.middleware.libertymutualmiddlewarechallenge.rest.controller.VoteDetails;
import com.middleware.libertymutualmiddlewarechallenge.rest.dto.in.pokersession.ConfirmRequestDto;
import com.middleware.libertymutualmiddlewarechallenge.rest.dto.in.pokersession.FullPokerSessionDetailsResponseDto;
import com.middleware.libertymutualmiddlewarechallenge.rest.dto.in.pokersession.JoinPokerSessionRequestDto;
import com.middleware.libertymutualmiddlewarechallenge.rest.dto.in.pokersession.NewPokerSessionRequestDto;
import com.middleware.libertymutualmiddlewarechallenge.rest.dto.in.story.NewStoryRequestDto;
import com.middleware.libertymutualmiddlewarechallenge.rest.dto.out.CollectionContainer;
import com.middleware.libertymutualmiddlewarechallenge.rest.dto.out.pokersession.PokerSessionDetailsResponseDto;
import com.middleware.libertymutualmiddlewarechallenge.rest.dto.out.story.FullStoryDetailsResponseDto;
import com.middleware.libertymutualmiddlewarechallenge.rest.dto.out.story.StoryDetailsResponseDto;
import com.middleware.libertymutualmiddlewarechallenge.service.business.member.MemberOperations;
import com.middleware.libertymutualmiddlewarechallenge.service.business.poker.PokerSessionOperations;
import com.middleware.libertymutualmiddlewarechallenge.service.business.story.StoryOperations;
import com.middleware.libertymutualmiddlewarechallenge.utils.GeneratorUtils;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@RequiredArgsConstructor
@Component
class PokerSessionManagementFacade implements PokerSessionOperations, StoryOperations, MemberOperations {

    public static final String STORY_ID_IS_REQUIRED = "Story id is required";
    public static final String MEMBER_ID_IS_REQUIRED = "Member id is required";
    private final PokerSessionRepository pokerSessionRepository;
    private final StoryRepository storyRepository;
    private final MemberRepository memberRepository;
    private final VoteRepository voteRepository;

    @Override
    public PokerSessionDetailsResponseDto createPokerSession(NewPokerSessionRequestDto request) {
        log.debug("Create new poker session {}", request);
        PokerSessionEntity entity = new PokerSessionEntity();
        // NOTE: Two sql queries generated here, due to the fact that we are using generated id
        entity.setId(GeneratorUtils.generateRandomString());
        entity.setTitle(request.getTitle());
        entity.setDeskType(request.getDeskType());
        entity.setCreatedAt(LocalDateTime.now());
        pokerSessionRepository.save(entity);

        log.debug("Poker session created {}", entity.getId());
        return new PokerSessionDetailsResponseDto(entity.getId(), entity.getTitle());
    }

    @Override
    public CollectionContainer<PokerSessionDetailsResponseDto> activePokerSessions() {
        log.info("Find all active poker sessions");

        var content = pokerSessionRepository.findAll().stream().map(entity -> new PokerSessionDetailsResponseDto(entity.getId(), entity.getTitle())).toList();

        log.debug("Found {} active poker sessions", content.size());
        return new CollectionContainer<>(content);
    }

    @Override
    public PokerSessionDetailsResponseDto joinPokerSession(String sessionId, JoinPokerSessionRequestDto request) {
        log.info("'{}' Joining poker session", request.nickname());

        var pokerSession = getPokerSession(sessionId);

        var member = new MemberEntity();
        member.setNickname(request.nickname());
        memberRepository.save(member);
        pokerSession.addMember(member);
        pokerSessionRepository.save(pokerSession);

        log.info("'{}' Joined poker session", request.nickname());
        return new PokerSessionDetailsResponseDto(sessionId.toString(), member.getId().toString());
    }

    @Override
    public FullPokerSessionDetailsResponseDto pokerSessionDetails(String sessionId) {
        var entity = getPokerSession(sessionId);

        var members = entity.getMembers().stream().map(MemberEntity::getNickname).collect(Collectors.toSet());
        var stories = entity.getStories().stream().map(s -> String.join(" | ", s.getProject(), s.getTitle(), s.getDescription())).collect(Collectors.toSet());

        return new FullPokerSessionDetailsResponseDto(entity.getId().toString(), entity.getTitle(), members, stories, entity.getCreatedAt());
    }

    @Transactional
    @Override
    public void removePokerSessionDetails(String sessionId, ConfirmRequestDto request) {
        Assert.state(request.confirm(), "Confirm is required");
        
        log.info("Remove poker session {}", sessionId);
        int stories = storyRepository.deleteAllByPokerSessionId(sessionId);
        log.info("Deleted {} stories by session id {}", stories, sessionId);

        var members = memberRepository.deleteAllByPokerSessionEntityId(sessionId);
        log.info("Deleted {} members", members);

        pokerSessionRepository.deleteById(sessionId);
    }

    private PokerSessionEntity getPokerSession(String sessionId) {
        return pokerSessionRepository.findById(sessionId).orElseThrow(() -> new RecordNotFound("Poker session not found by " + sessionId));
    }

    // ------- story operations -------

    @Override
    public StoryDetailsResponseDto createStory(NewStoryRequestDto request) {
        log.info("Create new story {}", request);
        var story = new StoryEntity();
        story.setTitle(request.title());
        story.setDescription(request.description());
        story.setProject(request.project());
        story.setVoteStatus(VoteStatus.PENDING);
        story.setPokerSession(getPokerSession(request.sessionId()));
        storyRepository.save(story);

        log.info("Story created {}", story.getId());
        return new StoryDetailsResponseDto(story.getId().toString(), story.getTitle(), story.getVoteStatus().name());
    }

    @Override
    public StoryDetailsResponseDto voteOnStory(Integer storyId, Integer memberId, Integer value) {
        Assert.notNull(storyId, STORY_ID_IS_REQUIRED);
        Assert.notNull(memberId, MEMBER_ID_IS_REQUIRED);

        var story = getStory(storyId);
        var member = getMember(memberId);

        Assert.state(story.getVoteStatus() == VoteStatus.VOTING, "Voting is not allowed for this story");

        voteRepository.findById(new VoteId(story.getId(), member.getId())).ifPresentOrElse(vote -> {
            voteRepository.delete(vote);
            log.info("Member {} removed vote on story {}", memberId, storyId);
        }, () -> {
            var vote = new VoteEntity();
            var voteId = new VoteId(story.getId(), member.getId());
            vote.setId(voteId);
            vote.setValue(value);
            voteRepository.save(vote);
            log.info("Member {} voted on story {}", memberId, storyId);
        });
        return new StoryDetailsResponseDto(story.getId().toString(), story.getTitle(), story.getVoteStatus().name());
    }

    @Override
    public FullStoryDetailsResponseDto storyDetails(Integer storyId) {
        var story = getStory(storyId);
        return new FullStoryDetailsResponseDto(story.getId().toString(), story.getTitle(), story.getVoteStatus().name(), story.getDescription(), story.getProject(), story.getPokerSession().getId(), story.getVotes().size());
    }

    @Override
    public void removeStory(Integer storyId) {
        log.info("Remove story {}", storyId);

        var story = getStory(storyId);
        Assert.state(story.getVoteStatus() == VoteStatus.PENDING, "Story in wrong state " + story.getVoteStatus());

        storyRepository.delete(story);
        log.info("Story {} removed", storyId);
    }

    @Override
    public StoryDetailsResponseDto startVoting(Integer storyId) {
        Assert.notNull(storyId, STORY_ID_IS_REQUIRED);

        var story = getStory(storyId);
        Assert.state(story.getVoteStatus() == VoteStatus.PENDING, "Story in wrong state " + story.getVoteStatus());

        story.setVoteStatus(VoteStatus.VOTING);
        storyRepository.save(story);

        log.info("Story {} started voting", storyId);
        return new StoryDetailsResponseDto(story.getId().toString(), story.getTitle(), story.getVoteStatus().name());
    }

    @Override
    public CollectionContainer<VoteDetails> voteDetails(Integer storyId, Integer memberId) {
        Assert.notNull(storyId, STORY_ID_IS_REQUIRED);
        Assert.notNull(memberId, MEMBER_ID_IS_REQUIRED);

        var story = getStory(storyId);
        if (story.getVoteStatus() == VoteStatus.VOTING) {
            log.info("Story {} in voting state", storyId);
            if (voteRepository.existsById(new VoteId(storyId, memberId))) {
                log.info("Member {} voted on story {}", memberId, storyId);
                var list = voteRepository.findAllByIdStoryId(storyId).stream().map(v -> new VoteDetails(v.getId().getStoryId(), v.getId().getMemberId(), v.getValue())).toList();
                return new CollectionContainer<>(list);
            }
            log.info("Member {} did not vote on story {}", memberId, storyId);
            return new CollectionContainer<>(List.of());
        }
        var list = voteRepository.findAllByIdStoryId(storyId).stream().map(v -> new VoteDetails(v.getId().getStoryId(), v.getId().getMemberId(), v.getValue())).toList();
        return new CollectionContainer<>(list);
    }

    @Override
    public StoryDetailsResponseDto finishVoting(Integer storyId) {
        Assert.notNull(storyId, STORY_ID_IS_REQUIRED);

        var story = getStory(storyId);
        Assert.state(story.getVoteStatus() == VoteStatus.VOTING, "Story in wrong state " + story.getVoteStatus());

        story.setVoteStatus(VoteStatus.VOTED);
        storyRepository.save(story);

        log.info("Story {} finished voting", storyId);
        return new StoryDetailsResponseDto(story.getId().toString(), story.getTitle(), story.getVoteStatus().name());
    }

    private StoryEntity getStory(Integer storyId) {
        return storyRepository.findById(storyId).orElseThrow(() -> new RecordNotFound("Story not found by " + storyId));
    }

    private MemberEntity getMember(Integer memberId) {
        return memberRepository.findById(memberId).orElseThrow(() -> new RecordNotFound("Member not found by " + memberId));
    }
}
