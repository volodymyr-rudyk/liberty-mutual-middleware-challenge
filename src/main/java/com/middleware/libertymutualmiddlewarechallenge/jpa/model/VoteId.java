package com.middleware.libertymutualmiddlewarechallenge.jpa.model;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Embeddable
public class VoteId implements Serializable {

    @Column(name = "story_id")
    private Integer storyId;
    @Column(name = "member_id")
    private Integer memberId;
}
