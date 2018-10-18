package ua.goit.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ua.goit.domain.Dish;
import ua.goit.domain.Menu;
import ua.goit.DAO.DishDao;
import ua.goit.DAO.MenuDao;
import ua.goit.repository.DishRepository;
import ua.goit.repository.MenuRepository;
import ua.goit.service.MenuService;

import java.util.List;

/**
 * Created by user on 01.12.2016.
 */
@Service
public class MenuServiceImpl implements MenuService{

    @Autowired
    private MenuDao menuDao;

    @Autowired
    private DishDao dishDao;

    @Autowired
    private MenuRepository menuRepository;

    @Autowired
    private DishRepository dishRepository;

    @Transactional
    public List<Menu> getAllMenus() {

        return menuRepository.findAll();
    }

    @Transactional
    public Menu addMenu(Menu menu) {

        Menu newMenu = new Menu();

        newMenu.setName(menu.getName());
        newMenu.setDishes(menu.getDishes());
        menuRepository.save(menu);

        return newMenu;
    }

    @Transactional
    public void deleteMenu(String name) {

        menuRepository.deleteByName(name);
    }

    @Transactional
    public Menu getByName(String name) {

        return menuRepository.findByName(name);
    }

    @Transactional
    public Dish addDishToMenu(String menuName, String dishName) {

        Dish dish = dishRepository.findByName(dishName);
        Menu menu = menuRepository.findByName(menuName);
        menu.getDishes().add(dish);
        menuRepository.save(menu);

        return dish;
    }

    @Transactional
    public void deleteDishFromMenu(String menuName, String dishName) {

        Menu menu = menuRepository.findByName(menuName);
        Dish dish = dishRepository.findByName(dishName);
        menu.getDishes().remove(dish);
        menuRepository.save(menu);
    }
}
