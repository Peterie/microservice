package com.callaars.peter.drinkservice.repository;
import com.callaars.peter.drinkservice.model.Drink;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface DrinkRepository extends JpaRepository<Drink, UUID> {

    Optional<Drink> findByName(String name);

    @Query("FROM Drink d WHERE :age >= d.minimumAge")
    List<Drink> findAvailableByAge(Integer age);

}
