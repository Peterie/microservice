package com.callaars.peter.barservice.service;

import com.callaars.peter.barservice.dto.UserDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.server.ResponseStatusException;
import reactor.core.publisher.Mono;

@Service
@RequiredArgsConstructor
public class BarService {

    private final WebClient.Builder webClientBuilder;

    public boolean userExists(String email) {
        UserDto userDto = webClientBuilder.build().get()
                .uri("http://user-service/users/" + email)
//                        , uriBuilder -> uriBuilder.queryParam("name", "values").build())
                .retrieve()
                .onStatus(HttpStatus::isError,
                        clientResponse -> {
                    throw new ResponseStatusException(clientResponse.statusCode());
                        })
                .bodyToMono(UserDto.class)
                .block();

        return userDto != null;
    }
}
