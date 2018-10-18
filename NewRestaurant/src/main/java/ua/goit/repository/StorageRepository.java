package ua.goit.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ua.goit.domain.Ingredient;
import ua.goit.domain.Storage;

import java.util.List;

public interface StorageRepository extends JpaRepository<Storage, Integer> {

    Storage findByIngredient_Name(String ingredientName);
    void deleteByIngredient_Name(String ingredientName);
    List<Storage> findAllByQuantityIsLessThan(Integer quantity);
}
