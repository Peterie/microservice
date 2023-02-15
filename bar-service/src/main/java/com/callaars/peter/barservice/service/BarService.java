package com.callaars.peter.barservice.service;

import com.callaars.peter.barservice.dto.DrinkDto;
import com.callaars.peter.barservice.dto.UserDrinkDto;
import com.callaars.peter.barservice.dto.UserDto;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.server.ResponseStatusException;
import reactor.core.publisher.Mono;

import java.time.LocalDate;
import java.time.Period;
import java.util.List;

@Service
@RequiredArgsConstructor
public class BarService {

    private final WebClient.Builder webClientBuilder;

    public boolean userExists(String email) {
        UserDto userDto = getUserDto(email);

        return userDto != null;
    }

    private UserDto getUserDto(String email) {
        return webClientBuilder.build().get()
                .uri("http://user-service/users/" + email)
//                        , uriBuilder -> uriBuilder.queryParam("name", "values").build())
                .retrieve()
                .onStatus(HttpStatus::isError,
                        clientResponse -> {
                            throw new ResponseStatusException(clientResponse.statusCode());
                        })
                .bodyToMono(UserDto.class)
                .block();
    }

    public boolean drinkExists(String name) {
        DrinkDto drinkDto = getDrinkDto(name);

        return drinkDto != null;
    }

    private DrinkDto getDrinkDto(String name) {
        DrinkDto drinkDto = webClientBuilder.build().get()
                .uri("http://drink-service/drinks/" + name)
                .retrieve()
                .onStatus(HttpStatus::isError,
                        clientResponse -> {
                    throw new ResponseStatusException(clientResponse.statusCode());
                        })
                .bodyToMono(DrinkDto.class)
                .block();
        return drinkDto;
    }

    public boolean checkIfUserMayDrink(UserDrinkDto userDrinkDto) {
        DrinkDto drinkDto = getDrinkDto(userDrinkDto.getName());
        UserDto userDto = getUserDto(userDrinkDto.getEmail());

        if (Period.between(userDto.getBirthDate(), LocalDate.now()).getYears() >= drinkDto.getMinimumAge()) {
            return true;
        } else {
            return false;
        }
    }

//    public Page<DrinkDto> getAvailableDrinks(String email) {
//        UserDto userDto = getUserDto(email);
//        String age = String.valueOf(Period.between(userDto.getBirthDate(), LocalDate.now()).getYears());
//        List<DrinkDto> drinkDtoList = webClientBuilder.build().get()
//                .uri("http://drink-service/drinks/available/" + age)
//                .retrieve()
//                .onStatus(HttpStatus::isError,
//                        clientResponse -> {
//                            throw new ResponseStatusException(clientResponse.statusCode());
//                        })
//                .bodyToFlux(DrinkDto.class)
//
//    }
}
