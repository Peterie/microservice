package com.callaars.peter.barservice.controller;

import com.callaars.peter.barservice.dto.DrinkDto;
import com.callaars.peter.barservice.dto.UserDrinkDto;
import com.callaars.peter.barservice.service.BarService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.Email;

@RestController
@RequestMapping("/bar")
@RequiredArgsConstructor
public class BarController {

    private final BarService barService;

    @GetMapping("/users/{email}")
    public boolean userExists(@PathVariable
                                  @Email(regexp = "^[\\w-\\.]+@([\\w-]+\\.)+[\\w-]{2,4}$")
                                  String email) {
        return barService.userExists(email);
    }

    @GetMapping("/drinks/{name}")
    public boolean drinkExists(@PathVariable String name) {
        return barService.drinkExists(name);
    }


    @GetMapping("/order/")
    public boolean checkIfUserMayDrink(@RequestBody UserDrinkDto userDrinkDto) {
        return barService.checkIfUserMayDrink(userDrinkDto);
    }

    @GetMapping("/users/showdrinks/{email}")
    public Page<DrinkDto> getAvailableDrinks(@PathVariable
                                                 @Email(regexp = "^[\\w-\\.]+@([\\w-]+\\.)+[\\w-]{2,4}$")
                                                 String email) {

        return barService.getAvailableDrinks(email);
    }




    // Create a GET method to check if a user is allowed to have a certain drink
    // query param user (email) (required), query param drink (name) (required)
    // Drink entity with the field UUID id, string name (unique), int minimumAge
    // Can be located in either the barservice or a whole new service

    // Create a GET Method to get a Page of Drinks, that a user can consume

    // Create a GET Method to get a Page of Users that can have a certain drink




}
