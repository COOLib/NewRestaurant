package ua.goit.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ua.goit.domain.Order;

import java.util.List;

public interface OrderRepository extends JpaRepository<Order, Integer> {

    List<Order> findAllByIsClosedEquals(String closed);
}
