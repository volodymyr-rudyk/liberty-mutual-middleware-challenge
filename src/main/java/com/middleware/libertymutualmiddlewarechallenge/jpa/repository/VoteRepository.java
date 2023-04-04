package com.middleware.libertymutualmiddlewarechallenge.jpa.repository;

import com.middleware.libertymutualmiddlewarechallenge.jpa.model.StoryEntity;
import com.middleware.libertymutualmiddlewarechallenge.jpa.model.VoteEntity;
import com.middleware.libertymutualmiddlewarechallenge.jpa.model.VoteId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface VoteRepository extends JpaRepository<VoteEntity, VoteId> {
    List<VoteEntity> findAllByIdStoryId(Integer storyId);
}
