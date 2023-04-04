package com.middleware.libertymutualmiddlewarechallenge.jpa.repository;

import com.middleware.libertymutualmiddlewarechallenge.jpa.model.MemberEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.stereotype.Repository;

@Repository
public interface MemberRepository extends JpaRepository<MemberEntity, Integer> {
    @Modifying
    int deleteAllByPokerSessionEntityId(String sessionId);
}
