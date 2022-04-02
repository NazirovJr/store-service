package com.liga.store.controller;

import com.liga.store.domain.Order;
import com.liga.store.domain.User;
import com.liga.store.service.OrderService;
import com.liga.store.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import javax.validation.Valid;
import java.util.List;
import java.util.Map;

/**
 * Customer order controller class.
 * This controller and related pages can be accessed by all users, regardless of their roles.
 * The @Controller annotation serves to inform Spring that this class is a bean and must be
 * loaded when the application starts.
 *
 * @author Nazirov Ilhomnjon (naziroffjr@gmail.com)
 * @version 1.0
 * @see Order
 * @see User
 * @see OrderService
 * @see UserService
 */
@Controller
@Slf4j
public class OrderController {
    /**
     * Service object for working with customer.
     */
    private final UserService userService;

    /**
     * Service object for working orders.
     */
    private final OrderService orderService;

    /**
     * Constructor for initializing the main variables of the cart controller.
     * The @Autowired annotation will allow Spring to automatically initialize objects.
     *
     * @param userService  service object for working with customer.
     * @param orderService service object for working orders.
     */
    @Autowired
    public OrderController(UserService userService, OrderService orderService) {
        this.userService = userService;
        this.orderService = orderService;
    }



    /**
     * Saves the customers order.
     * URL request {"/order"}, method POST.
     *
     * @param userSession   requested Authenticated customer.
     * @param bindingResult errors in validating http request.
     * @return order.
     */
    @PostMapping("/order")
    public Order postOrder(
            @AuthenticationPrincipal User userSession,
            @Valid Order validOrder,
            BindingResult bindingResult
    ) {
        User user = userService.findByUsername(userSession.getUsername());
        Order order = new Order(user);

        if (bindingResult.hasErrors()) {
            Map<String, String> errorsMap = ControllerUtils.getErrors(bindingResult);

            return null;
        } else {
            order.getGoodList().addAll(user.getGoodList());
            order.setTotalPrice(validOrder.getTotalPrice());
            order.setFirstName(validOrder.getFirstName());
            order.setLastName(validOrder.getLastName());
            order.setCity(validOrder.getCity());
            order.setAddress(validOrder.getAddress());
            order.setPostIndex(validOrder.getPostIndex());
            order.setEmail(validOrder.getEmail());
            order.setPhoneNumber(validOrder.getPhoneNumber());

            user.getGoodList().clear();

            orderService.save(order);

            log.debug("User {} id={} made an order: FirstName={}, LastName={}, TotalPrice={}, City={}, " +
                            "Address={}, PostIndex={}, Email={}, PhoneNumber={}",
                    user.getUsername(), user.getId(), order.getFirstName(), order.getLastName(), order.getTotalPrice(),
                    order.getCity(), order.getAddress(), order.getPostIndex(), order.getEmail(), order.getPhoneNumber());
        }

        return order;
    }

    /**
     * Returns the finalize order.
     * URL request {"/finalizeOrder"}, method GET.
     *
     * * @return finalizeOrder.
     */
    @GetMapping("/finalizeOrder")
    public Order finalizeOrder() {
        List<Order> orderList = orderService.findAll();
        Order orderIndex = orderList.get(orderList.size() - 1);

        return orderIndex;
    }

    /**
     * Returns all customers orders.
     * URL request {"/userOrders"}, method GET.
     *
     * @param userSession requested Authenticated customer.
     * @return orders.
     */
    @GetMapping("/userOrders")
    public List<Order> getUserOrdersList(@AuthenticationPrincipal User userSession) {
        User userFromDB = userService.findByUsername(userSession.getUsername());
        List<Order> orders = orderService.findOrderByUser(userFromDB);

        return orders;
    }

    /**
     * Returns all orders of all customers.
     * URL request {"/orders"}, method GET.
     *
     * @return orders.
     */
    @GetMapping("/orders")
    public List<Order> getAllOrdersList() {
        List<Order> orders = orderService.findAll();

        return orders;
    }
}
