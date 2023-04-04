package com.middleware.libertymutualmiddlewarechallenge.handler;

import com.middleware.libertymutualmiddlewarechallenge.exception.AbstractRestApiException;
import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ProblemDetail;
import org.springframework.validation.BindException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.Objects;

@RestControllerAdvice
public class ErrorHandlerAdvice {

    @ExceptionHandler
    ProblemDetail handle(Exception e) {
        ProblemDetail problemDetail = ProblemDetail.forStatus(500);
        problemDetail.setDetail(e.getMessage());
        return problemDetail;
    }

    @ExceptionHandler
    ProblemDetail handle(BindException e) {
        var problemDetail = ProblemDetail.forStatus(HttpStatusCode.valueOf(400));
        problemDetail.setDetail(e.getMessage());
        var errors = e.getAllErrors().stream()
                .map(DefaultMessageSourceResolvable::getDefaultMessage)
                .filter(Objects::nonNull)
                .sorted().toList().toString();
        problemDetail.setDetail(errors);
        return problemDetail;
    }

    @ExceptionHandler
    ProblemDetail handle(IllegalArgumentException e) {
        var problemDetail = ProblemDetail.forStatus(HttpStatusCode.valueOf(400));
        problemDetail.setDetail(e.getMessage());
        return problemDetail;
    }

    @ExceptionHandler
    ProblemDetail handle(AbstractRestApiException e) {
        var problemDetail = ProblemDetail.forStatus(HttpStatusCode.valueOf(e.getStatus()));
        problemDetail.setDetail(e.getMessage());
        return problemDetail;
    }
}
