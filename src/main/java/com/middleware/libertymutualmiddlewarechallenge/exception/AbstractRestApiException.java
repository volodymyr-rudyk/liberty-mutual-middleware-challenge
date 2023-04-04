package com.middleware.libertymutualmiddlewarechallenge.exception;

import lombok.Getter;

public abstract class AbstractRestApiException extends RuntimeException {

    @Getter
    protected final int status;

    public AbstractRestApiException(int status, String message) {
        super(message);
        this.status = status;
    }
}
