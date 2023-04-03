package com.middleware.libertymutualmiddlewarechallenge.handler;

import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ProblemDetail;
import org.springframework.validation.BindException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

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
        var errors = e.getAllErrors().stream().map(DefaultMessageSourceResolvable::getDefaultMessage).sorted().toList().toString();
        problemDetail.setDetail(errors);
        return problemDetail;
    }

}
