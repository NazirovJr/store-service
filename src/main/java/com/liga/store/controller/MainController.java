package com.liga.store.controller;

import com.liga.store.domain.Good;
import com.liga.store.service.GoodService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

/**
 * Home page controller class.
 * This controller and related pages can be accessed by all users, regardless of their roles.
 * The @Controller annotation serves to inform Spring that this class is a bean and must be
 * loaded when the application starts.
 *
 * @author Nazirov Ilhomnjon (naziroffjr@gmail.com)
 * @version 1.0
 * @see Good
 * @see GoodService
 */
@Controller
public class MainController {
    /**
     * Service object for working with products.
     */
    private final GoodService goodService;

    /**
     * Constructor for initializing the main variables of the product controller.
     * The @Autowired annotation will allow Spring to automatically initialize objects.
     *
     * @param goodService Service object for working with products.
     */
    @Autowired
    public MainController(GoodService goodService) {
        this.goodService = goodService;
    }

    /**
     * Returns all products to the main page.
     * URL request {"/"}, method GET.
     *
     * @return good.
     */
    @GetMapping("/")
    public List<Good> home() {
        List<Good> goods = goodService.findAll();

        return goods;
    }

    /**
     * product that matches the input id parameter.
     * method GET.
     *
     * @param good product "id" to be returned to the page.
     * @return product page with model attributes.
     */
    @GetMapping("/product/{id}")
    public Good getProduct(@PathVariable("id") Good good) {
        return good;
    }
}
