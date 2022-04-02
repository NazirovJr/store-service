package com.liga.store.controller;

import com.liga.store.domain.User;
import com.liga.store.exeptions.BadRequestException;
import com.liga.store.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.client.RestTemplate;

import javax.validation.Valid;
import java.util.Map;

/**
 * Registration controller class.
 * This controller and related pages can be accessed by all users, regardless of their roles.
 * The @Controller annotation serves to inform Spring that this class is a bean and must be
 * loaded when the application starts.
 *
 * @author Nazirov Ilhomnjon (naziroffjr@gmail.com)
 * @version 1.0
 * @see User
 * @see UserService
 * @see RestTemplate
 */
@Controller
@Slf4j
public class RegistrationController {

    /**
     * Service object for working with users.
     */
    private final UserService userService;

    /**
     * Object which offers templates for common scenarios by HTTP method.
     */
    private final RestTemplate restTemplate;



    /**
     * Constructor for initializing the main variables of the cart controller.
     * The @Autowired annotation will allow Spring to automatically initialize objects.
     *
     * @param userService  service object for working with users.
     * @param restTemplate object which offers templates for common scenarios by HTTP method.
     */
    @Autowired
    public RegistrationController(UserService userService, RestTemplate restTemplate) {
        this.userService = userService;
        this.restTemplate = restTemplate;
    }

    /**
     * Returns registration page.
     * URL request {"/registration"}, method GET.
     *
     * @return registration page.
     */
    @GetMapping("/registration")
    public String registration() {
        return "registration";
    }

    /**
     * Saves user credentials and redirect to login page.
     * URL request {"/registration"}, method POST.
     *
     * @param passwordConfirm user password.
     * @param user            valid user.
     * @param bindingResult   errors in validating http request.
     * @return redirect to login page with model attributes.
     */
    @PostMapping("/registration")
    public User registration(
            @RequestParam("password2") String passwordConfirm,
            @Valid User user,
            BindingResult bindingResult
    ) {


        boolean isConfirmEmpty = StringUtils.isEmpty(passwordConfirm);
        boolean isPasswordDifferent = user.getPassword() != null && !user.getPassword().equals(passwordConfirm);

        if (isConfirmEmpty) {
            throw  new BadRequestException("Подтверждение пароля не может быть пустым");
        }

        if (isPasswordDifferent) {
            throw new BadRequestException("Пароли не совпадают");
        }

        if (bindingResult.hasErrors()) {
            Map<String, String> errors = ControllerUtils.getErrors(bindingResult);

            throw new BadRequestException("" + errors);
        }

        if (!userService.addUser(user)) {
            throw new BadRequestException("usernameError" + "Пользователь существует!");
        }

        log.debug("User {} registered", user.getUsername());

        return user;
    }

}