package com.middleware.libertymutualmiddlewarechallenge.jpa.repository;

import com.middleware.libertymutualmiddlewarechallenge.jpa.model.StoryEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface StoryRepository extends JpaRepository<StoryEntity, Integer> {
    @Modifying(clearAutomatically = true, flushAutomatically = true)
    int deleteAllByPokerSessionId(String sessionId);

    List<StoryEntity> findAllByPokerSessionId(String sessionId);
}
