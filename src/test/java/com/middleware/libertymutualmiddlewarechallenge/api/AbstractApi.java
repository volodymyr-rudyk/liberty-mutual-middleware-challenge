package com.middleware.libertymutualmiddlewarechallenge.api;

import com.middleware.libertymutualmiddlewarechallenge.utils.JsonUtils;
import lombok.SneakyThrows;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.mock.web.MockHttpServletResponse;

public abstract class AbstractApi {

    protected boolean is2XX(int status) {
        return status >= 200 && status <= 299;
    }

    @SneakyThrows
    protected <T> ResponseEntity<T> handle(MockHttpServletResponse response, Class<T> clazz) {
        return new ResponseEntity<T>(
                JsonUtils.fromJson(response.getContentAsString(), clazz),
                HttpStatusCode.valueOf(response.getStatus()));
    }

}
