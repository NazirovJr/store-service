package com.liga.store.repos;

import com.liga.store.domain.Order;
import com.liga.store.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * A repository for {@link Order} objects providing a set of JPA methods for working with the database.
 * Inherits interface {@link JpaRepository}.
 *
 * @author Nazirov Ilhomnjon (naziroffjr@gmail.com)
 * @version 1.0
 * @see Order
 */
public interface OrderRepository extends JpaRepository<Order, Long>  {
    /**
     * Returns list of orders authenticated user.
     *
     * @param user name of authenticated user.
     * @return An object of type {@link List} is a list of orders of authenticated user.
     */
    List<Order> findOrderByUser(User user);
}