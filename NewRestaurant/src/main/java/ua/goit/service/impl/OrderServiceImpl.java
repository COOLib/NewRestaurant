package ua.goit.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ua.goit.domain.Dish;
import ua.goit.domain.Order;
import ua.goit.domain.Waiter;
import ua.goit.DAO.DishDao;
import ua.goit.DAO.OrderDao;
import ua.goit.repository.DishRepository;
import ua.goit.repository.OrderRepository;
import ua.goit.service.OrderService;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by user on 01.12.2016.
 */
@Service
public class OrderServiceImpl implements OrderService{

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private DishRepository dishRepository;

    @Autowired
    private DishDao dishDao;

    @Transactional
    public Order addOrder(Order order) {

        Order newOrder = new Order();
        newOrder.setWaiter(order.getWaiter());
        newOrder.setDishes(order.getDishes());
        newOrder.setTableNumber(order.getTableNumber());
        newOrder.setDate(new Date());
        newOrder.setIsClosed("opened");

        orderRepository.save(order);
        return newOrder;
    }

    @Transactional
    public void deleteOrder(int id) {

        orderRepository.deleteById(id);
    }

    @Transactional
    public void turnToClosed(int id) {

        Order order = getById(id);
        order.setIsClosed("closed");
        orderRepository.save(order);
    }

    @Transactional
    public Order getById(Integer id) {

        return orderRepository.findById(id).get();
    }

    @Transactional
    public List<Order> getAllClosed() {

        return orderRepository.findAllByIsClosedEquals("closed");
    }

    @Transactional
    public List<Order> getAllOpened() {

        return orderRepository.findAllByIsClosedEquals("opened");
    }

    @Transactional
    public Dish addDishToOrder(int orderId, String dishName) {

        Dish dish = dishRepository.findByName(dishName);
        Order order = orderRepository.getOne(orderId);
        order.getDishes().add(dish);
        orderRepository.save(order);
        return dish;
    }

    @Transactional
    public void deleteDishFromOrder(int orderId, String dishName) {

        Order order = orderRepository.getOne(orderId);
        Dish dish = dishRepository.findByName(dishName);
        order.getDishes().remove(dish);
        orderRepository.save(order);
    }
}
