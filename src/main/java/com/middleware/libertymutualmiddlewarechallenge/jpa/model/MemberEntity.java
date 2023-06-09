package com.middleware.libertymutualmiddlewarechallenge.jpa.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@Entity(name = "members")
public class MemberEntity implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Basic
    @Column(name = "nickname")
    private String nickname;

    @ManyToMany(mappedBy = "members")
    Set<PokerSessionEntity> pokerSessionEntity = new HashSet<>();

    @OneToMany(mappedBy = "member", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    private Set<VoteEntity> votes = new HashSet<>();
}
