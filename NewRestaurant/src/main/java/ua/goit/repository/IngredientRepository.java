package ua.goit.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ua.goit.domain.Ingredient;

import java.util.List;

public interface IngredientRepository extends JpaRepository<Ingredient, Integer> {

    Ingredient findByName(String name);
}
