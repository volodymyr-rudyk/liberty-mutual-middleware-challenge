package com.middleware.libertymutualmiddlewarechallenge.rest.dto.out;

import java.util.Collection;
import java.util.Collections;

public record CollectionContainer<T>(Collection<T> content) {
    public CollectionContainer {
        content = Collections.unmodifiableCollection(content);
    }
}