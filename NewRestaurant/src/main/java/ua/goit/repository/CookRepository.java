package ua.goit.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ua.goit.domain.Cook;

/**
 * Created by user on 29.03.2018.
 */
public interface CookRepository extends JpaRepository<Cook, Integer> {
}
