package ua.goit.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ua.goit.domain.Cook;
import ua.goit.domain.Employee;
import ua.goit.domain.Waiter;
import ua.goit.repository.CookRepository;
import ua.goit.repository.EmployeeRepository;
import ua.goit.repository.WaiterRepository;
import ua.goit.service.EmployeeService;

import java.util.List;

/**
 * Created by user on 08.11.2016.
 */
@Service
public class EmployeeServiceImpl  implements EmployeeService{

    @Autowired
    private CookRepository cookRepository;

    @Autowired
    private WaiterRepository waiterRepository;

    @Autowired
    private EmployeeRepository employeeRepository;

    @Transactional
    public Employee addEmployee(Employee employee) {

        employeeRepository.save(employee);
        return employee;
    }

    @Transactional
    public Waiter addWaiter(Waiter waiter) {

        waiterRepository.save(waiter);
        return waiter;
    }

    @Transactional
    public Cook addCook(Cook cook) {

        cookRepository.save(cook);
        return cook;
    }

    @Transactional
    public void deleteEmployee(String name) {

        employeeRepository.deleteByName(name);
    }

    @Transactional
    public List<Employee> getByName(String name) {

        return employeeRepository.findByName(name);
    }

    @Transactional
    public List<Employee> getAllEmployees() {

        return employeeRepository.findAll();
    }
}
