package com.callaars.peter.drinkservice.controller;

import com.callaars.peter.drinkservice.dto.DrinkDto;
import com.callaars.peter.drinkservice.dto.DrinkDtoMapper;
import com.callaars.peter.drinkservice.model.Drink;
import com.callaars.peter.drinkservice.service.DrinkService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/drinks")
@RequiredArgsConstructor
@Validated
public class DrinkController {

    private final DrinkService drinkService;
    private final DrinkDtoMapper drinkDtoMapper;

    @GetMapping
    public List<DrinkDto> getAllDrinks() {
        return drinkService.getAllDrinks()
                .stream()
                .map(drinkDtoMapper::toDto)
                .collect(Collectors.toList());
    }

    @GetMapping("/{name}")
    public DrinkDto getDrinkByName(@PathVariable String name) {

        Drink drink = drinkService.getDrinkByName(name);

        return drinkDtoMapper.toDto(drink);
    }

    @GetMapping("/available/{age}")
    public List<DrinkDto> getAvailableDrinks(@PathVariable Integer age) {
        List<Drink> drinkList = drinkService.getAvailableDrinks(age);

        return drinkList
                .stream()
                .map(drinkDtoMapper::toDto)
                .collect(Collectors.toList());
    }

}