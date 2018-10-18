package ua.goit.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ua.goit.domain.Menu;

public interface MenuRepository extends JpaRepository<Menu, Integer> {

    Menu findByName(String name);
    void deleteByName(String name);
}
