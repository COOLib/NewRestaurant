package ua.goit.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import ua.goit.domain.Cook;
import ua.goit.domain.Position;

/**
 * Created by user on 29.03.2018.
 */
public interface CookRepository extends JpaRepository<Cook, Integer> {
}
