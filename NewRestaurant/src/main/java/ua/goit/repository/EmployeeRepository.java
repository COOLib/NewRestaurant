package ua.goit.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ua.goit.domain.Employee;

import java.util.List;

public interface EmployeeRepository extends JpaRepository<Employee, Integer> {

    List<Employee> findByName(String name);
    void deleteByName(String name);
}
