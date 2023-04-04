package com.middleware.libertymutualmiddlewarechallenge.jpa.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@Entity(name = "poker_sessions")
public class PokerSessionEntity {
    @Id
    private String id;

    @Basic
    @Column(name = "title")
    private String title;

    @Basic
    @Column(name = "deskType")
    private String deskType;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "createdAt")
    LocalDateTime createdAt;

    @ManyToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE}, fetch = FetchType.LAZY)
    @JoinTable(
            name = "poker_sessions_members",
            joinColumns = {@JoinColumn(name = "poker_session_id")},
            inverseJoinColumns = {@JoinColumn(name = "member_id")}
    )
    Set<MemberEntity> members = new HashSet<>();

    @OneToMany(mappedBy = "pokerSession", fetch = FetchType.LAZY)
    Set<StoryEntity> stories;

    public void addMember(MemberEntity member) {
        members.add(member);
        member.getPokerSessionEntity().add(this);
    }

    public void removeMember(MemberEntity member) {
        members.remove(member);
        member.getPokerSessionEntity().remove(this);
    }

}
