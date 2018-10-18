package ua.goit.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ua.goit.domain.CookedDish;

public interface CookedDishreposiory extends JpaRepository<CookedDish, Integer> {
}
