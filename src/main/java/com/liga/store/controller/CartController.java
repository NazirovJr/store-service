package com.liga.store.controller;

import com.liga.store.domain.Good;
import com.liga.store.domain.User;
import com.liga.store.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * Customer shopping cart controller class.
 * This controller and related pages can be accessed by all users, regardless of their roles.
 * The @Controller annotation serves to inform Spring that this class is a bean and must be
 * loaded when the application starts.
 *
 * @author Nazirov Ilhomnjon (naziroffjr@gmail.com)
 * @version 1.0
 * @see User
 * @see UserService
 */
@Controller
public class CartController {
    /**
     * Service object for working with customer shopping cart.
     */
    private final UserService userService;

    /**
     * Constructor for initializing the main variables of the cart controller.
     * The @Autowired annotation will allow Spring to automatically initialize objects.
     *
     * @param userService service object for working with user shopping cart.
     */
    @Autowired
    public CartController(UserService userService) {
        this.userService = userService;
    }

    /**
     * Returns customer shopping cart.
     * URL request {"/cart"}, method GET.
     *
     * @param userSession requested Authenticated customer.
     * @return cart.
     */
    @GetMapping("/cart")
    public User getCart(@AuthenticationPrincipal User userSession) {
        return userService.findByUsername(userSession.getUsername());
    }

    /**
     * Adds a product to the customer shopping cart and redirects it to "/cart".
     * URL request {"/cart/add"}, method POST.
     *
     * @param good     the product to add to the cart.
     * @param userSession request Authenticated customer.
     * @return user.
     */
    @PostMapping("/cart/add")
    public User addToCart(
            @RequestParam("add") Good good,
            @AuthenticationPrincipal User userSession
    ) {
        User user = userService.findByUsername(userSession.getUsername());
        user.getGoodList().add(good);
        userService.save(user);

        return user;
    }

    /**
     * Remove product from customer shopping cart and redirects it to "/cart".
     * URL request {"/cart/remove"}, method POST.
     *
     * @param good     the product to be removed from the customer shopping cart.
     * @param userSession request Authenticated customer.
     * @return гыук.
     */
    @PostMapping("/cart/remove")
    public User removeFromCart(
            @RequestParam(value = "perfumeId") Good good,
            @AuthenticationPrincipal User userSession
    ) {
        User user = userService.findByUsername(userSession.getUsername());

        if (good != null) {
            user.getGoodList().remove(good);
        }

        userService.save(user);

        return user;
    }
}