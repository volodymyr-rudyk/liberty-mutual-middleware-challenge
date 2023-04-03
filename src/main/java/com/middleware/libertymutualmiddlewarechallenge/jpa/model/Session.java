package com.middleware.libertymutualmiddlewarechallenge.jpa.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.UuidGenerator;

import java.util.UUID;


@Getter
@Setter
@Entity
public class Session {
    @Id
    @GeneratedValue
    @UuidGenerator
    private UUID id;

}
