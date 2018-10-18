package ua.goit.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ua.goit.domain.Dish;

import java.util.List;

public interface DishRepository extends JpaRepository<Dish, Integer> {

    Dish findByName(String name);
    void deleteByName(String name);
}
