package com.callaars.peter.drinkservice.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.validation.constraints.Size;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class DrinkDto {

    @Size(max = 50)
    @Column(unique = true)
    private String name;

    @Column(name = "minimum_age")
    private Integer minimumAge;

//    @Column(name = "price_in_cents")
//    @Min(value = 0, message = "Price should not be lower than 0 cents")
//    private Integer priceInCents;

}
