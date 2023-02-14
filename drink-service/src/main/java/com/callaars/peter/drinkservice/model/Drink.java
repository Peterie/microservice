package com.callaars.peter.drinkservice.model;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.Min;
import javax.validation.constraints.Size;
import java.util.UUID;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name = "drinks")
public class Drink {

    @Id
    @GeneratedValue
    private UUID id;

    @Size(max = 50)
    @Column(unique = true)
    private String name;

    @Column(name = "minimum_age")
    private Integer minimumAge;

//    @Column(name = "price_in_cents")
//    @Min(value = 0, message = "Price should not be lower than 0 cents")
//    private Integer priceInCents;

}
