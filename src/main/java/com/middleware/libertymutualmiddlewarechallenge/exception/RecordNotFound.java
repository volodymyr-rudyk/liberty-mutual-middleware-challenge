package com.middleware.libertymutualmiddlewarechallenge.exception;

public class RecordNotFound extends AbstractRestApiException {
    public static final int STATUS_CODE = 404;

    public RecordNotFound(String message) {
        super(STATUS_CODE, message);
    }
}
