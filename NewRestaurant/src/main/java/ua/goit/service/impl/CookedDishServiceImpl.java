package ua.goit.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ua.goit.DAO.*;
import ua.goit.controller.HCookedDishController;
import ua.goit.domain.*;
import ua.goit.repository.*;
import ua.goit.service.CookedDishService;

import java.util.Date;
import java.util.List;

/**
 * Created by user on 05.12.2016.
 */
@Service
public class CookedDishServiceImpl implements CookedDishService {

    @Autowired
    private CookedDishreposiory cookedDishreposiory;

    @Autowired
    private EmployeeRepository employeeRepository;

    @Autowired
    private DishRepository dishRepository;

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private StorageRepository storageRepository;

    @Transactional
    public void addCookedDish(String employeeName, String dishName, int orderNumber) {

        CookedDish cookedDish = new CookedDish();
        Dish dish = dishRepository.findByName(dishName);

        cookedDish.setCook((Cook) employeeRepository.findByName(employeeName));
        cookedDish.setId(dish.getId());
        cookedDish.setDate(new Date());

        if (orderRepository.findById(orderNumber).get().getDishes().contains(dishRepository.findByName(dishName))) {

            cookedDish.setOrderNumber(orderNumber);
            List<Ingredient> ingredients = dish.getIngredient();

            for (Ingredient i : ingredients) {
                Storage storage = storageRepository.findByIngredient_Name(i.getName());
                int realQuantity = storage.getQuantity();
                if (realQuantity - 1 >= 0) {
                    storage.setQuantity(realQuantity - 1);
                } else {

                    throw new RuntimeException("Cannot update quantity at storage, because it will be less than zero.");
                }
            }

            cookedDishreposiory.save(cookedDish);
        } else {
            String s = "Order " + orderNumber + " doesn't contain a dish named " + dishName +
                    ". This dish can't be added to the list of the cooked dishes.";

            throw new RuntimeException(s);
        }
    }

    @Transactional
    public List<CookedDish> getAllCookedDishes() {

        return cookedDishreposiory.findAll();
    }
}
