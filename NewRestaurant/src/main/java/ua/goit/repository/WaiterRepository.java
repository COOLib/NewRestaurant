package ua.goit.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ua.goit.domain.Waiter;

public interface WaiterRepository extends JpaRepository<Waiter, Integer> {
}
