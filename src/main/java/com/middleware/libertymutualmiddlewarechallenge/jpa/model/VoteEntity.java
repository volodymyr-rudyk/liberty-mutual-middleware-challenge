package com.middleware.libertymutualmiddlewarechallenge.jpa.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity(name = "votes")
public class VoteEntity {

    @EmbeddedId
    private VoteId id;

    @Column(name = "vote_value", nullable = false)
    private Integer value;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "story_id", insertable = false, updatable = false)
    private StoryEntity story;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id", insertable = false, updatable = false)
    private MemberEntity member;

}
