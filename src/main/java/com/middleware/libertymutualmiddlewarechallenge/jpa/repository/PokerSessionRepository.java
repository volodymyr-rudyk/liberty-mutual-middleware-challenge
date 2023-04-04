package com.middleware.libertymutualmiddlewarechallenge.jpa.repository;

import com.middleware.libertymutualmiddlewarechallenge.jpa.model.PokerSessionEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PokerSessionRepository extends JpaRepository<PokerSessionEntity, String> {
}
