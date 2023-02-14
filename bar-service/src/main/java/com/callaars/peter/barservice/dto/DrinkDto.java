package com.callaars.peter.barservice.dto;

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
}
