package com.middleware.libertymutualmiddlewarechallenge.utils;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.SneakyThrows;
import lombok.experimental.UtilityClass;

@UtilityClass
public class JsonUtils {
    public static final ObjectMapper objectMapper = new ObjectMapper();

    @SneakyThrows
    public static String toJson(Object request) {
        return objectMapper.writeValueAsString(request);
    }

    @SneakyThrows
    public  <T> T fromJson(String payload, Class<T> clazz) {
        return objectMapper.readValue(payload, clazz);
    }
}
