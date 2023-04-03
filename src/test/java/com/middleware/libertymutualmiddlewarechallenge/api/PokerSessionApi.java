package com.middleware.libertymutualmiddlewarechallenge.api;

import com.middleware.libertymutualmiddlewarechallenge.rest.dto.request.NewPokerSessionRequestDto;
import com.middleware.libertymutualmiddlewarechallenge.utils.JsonUtils;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;

@RequiredArgsConstructor
@Component
public class PokerSessionApi extends AbstractApi {
    private final MockMvc mockMvc;

    @SneakyThrows
    public <O, I> ResponseEntity<O> createNewPokerSession(NewPokerSessionRequestDto request, Class<O> out) {
        var response = mockMvc.perform(post("/sessions")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(JsonUtils.toJson(request)))
                .andDo(print())
                .andReturn()
                .getResponse();

        return super.handle(response, out);
    }

}
