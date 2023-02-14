package com.callaars.peter.drinkservice.service;

import com.callaars.peter.drinkservice.dto.DrinkDto;
import com.callaars.peter.drinkservice.model.Drink;
import com.callaars.peter.drinkservice.repository.DrinkRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
@RequiredArgsConstructor
public class DrinkService {

    private final DrinkRepository drinkRepository;

    public Drink getDrinkByName(String name) {
        return drinkRepository.findByName(name)
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND,
                        "Drink with name '@s' could not be found".formatted(name)
                ));
    }

    public List<Drink> getAvailableDrinks(Integer age) {
        return drinkRepository.findAvailableByAge(age);
    }

    public List<Drink> getAllDrinks() {
        return drinkRepository.findAll();
    }
}
