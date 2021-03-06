package com.liga.store.service;

import com.liga.store.domain.Order;
import com.liga.store.domain.User;
import com.liga.store.service.Impl.OrderServiceImpl;

import java.util.List;

/**
 * The service layer interface describes a set of methods for working with objects of the {@link Order} class.
 *
 * @author Nazirov Ilhomnjon (naziroffjr@gmail.com)
 * @version 1.0
 * @see Order
 * @see OrderServiceImpl
 */
public interface OrderService {
    /**
     * Return list of all user orders.
     *
     * @return list of user {@link Order}.
     */
    List<Order> findAll();

    /**
     * Save order info.
     *
     * @param order order object to return.
     * @return The {@link Order} class object which will be saved in the database.
     */
    Order save(Order order);

    /**
     * Returns list of orders authenticated user.
     *
     * @param user name of authenticated user.
     * @return An object of type {@link List} is a list of orders of authenticated user.
     */
    List<Order> findOrderByUser(User user);
}
