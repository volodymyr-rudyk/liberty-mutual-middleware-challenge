package com.middleware.libertymutualmiddlewarechallenge.jpa.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.HashSet;
import java.util.Set;


@Getter
@Setter
@Entity(name = "stories")
public class StoryEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Basic
    @Column(name = "title", nullable = false, length = 255)
    private String title;

    @Basic
    @Column(name = "description", nullable = false, length = 255)
    private String description;
    @Basic

    @Column(name = "project", nullable = false, length = 255)
    private String project;

    @Enumerated(EnumType.STRING)
    @Column(name = "vote_status", nullable = false, length = 255)
    private VoteStatus voteStatus;

    @OneToMany(mappedBy = "story", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    private Set<VoteEntity> votes;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "poker_session_id")
    PokerSessionEntity pokerSession;
}
