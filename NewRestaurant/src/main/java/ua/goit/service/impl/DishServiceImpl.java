package ua.goit.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ua.goit.domain.Dish;
import ua.goit.repository.DishRepository;
import ua.goit.service.DishService;

import java.util.List;

/**
 * Created by user on 16.11.2016.
 */
@SuppressWarnings("SpringJavaAutowiringInspection")
@Service
public class DishServiceImpl implements DishService {

    @Autowired
    private DishRepository dishRepository;

    @Transactional
    public Dish addDish(Dish dish) {

        dishRepository.save(dish);
        return dish;
    }

    @Transactional
    public Dish getByName(String name) {

        return dishRepository.findByName(name);
    }

    @Transactional
    public List<Dish> getAllDishes() {

        return dishRepository.findAll();
    }

    @Transactional
    public void deleteDish(String name) {

        dishRepository.deleteByName(name);
    }
}
