package com.liga.store.controller;

import com.liga.store.domain.Good;
import com.liga.store.domain.User;
import com.liga.store.exeptions.BadRequestException;
import com.liga.store.service.GoodService;
import com.liga.store.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;
import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.UUID;

/**
 * User controller class.
 * The @Controller annotation serves to inform Spring that this class is a bean and must be
 * loaded when the application starts.
 *
 * @author Nazirov Ilhomnjon (naziroffjr@gmail.com)
 * @version 1.0
 * @see User
 * @see Good
 * @see UserService
 * @see GoodService
 */
@Controller
@Slf4j
@RequestMapping("/user")
public class UserController {
    /**
     * Service object for working with users.
     */
    private final UserService userService;

    /**
     * Service object for working with products.
     */
    private final GoodService goodService;

    /**
     * Upload path for images.
     * It will be required for front-end developer in the feature
     */
    @Value("${upload.path}")
    private String uploadPath;

    /**
     * Constructor for initializing the main variables of the cart controller.
     * The @Autowired annotation will allow Spring to automatically initialize objects.
     *
     * @param userService    service object for working with users.
     * @param goodService service object for working with products.
     */
    @Autowired
    public UserController(UserService userService, GoodService goodService) {
        this.userService = userService;
        this.goodService = goodService;
    }

    /**
     * Returns a list of products for editing by an administrator.
     * The @PreAuthorize annotation says the controller is accessible
     * only to users with administrator rights.
     * URL request {"/productlist"}, method GET.
     *
     * @return productList.
     */
    @GetMapping("productlist")
    public List<Good> getAllProducts() {
        List<Good> page = goodService.findAll();

        return page;
    }

    /**
     * Returns a product for editing by an administrator.
     * The @PreAuthorize annotation says the controller is accessible
     * only to users with administrator rights.
     * URL request {"/productlist/{good}"}, method GET.
     *
     * @param good the product to editing.
     * @return productEdit.
     */
    @PreAuthorize("hasAuthority('ADMIN', 'OWNER')")
    @PostMapping("productlist")
    public Good editProduct(@RequestBody Good good) {
        goodService.save(good);
        return good;
    }

    /**
     * Save edited product to the database by an administrator.
     * The @PreAuthorize annotation says the controller is accessible
     * only to users with administrator rights.
     * URL request {"/productlist"}, method POST.
     *
     * @param good edited product.
     */
    @PreAuthorize("hasAuthority('ADMIN', 'OWNER')")
    @PostMapping("productlist")
    public void saveEditedProduct(Good good) throws IOException {

        goodService.saveProductInfoById(good.getTitle(), good.getProducer(), good.getYear(),
                good.getCountry(), good.getDescription(), good.getPrice(),
           good.getType(), good.getId());

        log.debug("ADMIN save edited product to DB: id={}, perfumer={}, good={}",
                good.getId(), good.getProducer(), good.getTitle());
    }



    /**
     * Save new product to the database by an administrator.
     * The @PreAuthorize annotation says the controller is accessible
     * only to users with administrator rights.
     * URL request {"/add"}, method POST.
     *
     * @param good       saved product.
     * @param bindingResult errors in validating http request.
     * @return addToDb.
     */
    @PreAuthorize("hasAuthority('ADMIN', 'OWNER')")
    @PostMapping("add")
    public Good addProductToBd(
            @Valid Good good,
            BindingResult bindingResult
    ) throws IOException {
        if (bindingResult.hasErrors()) {
            Map<String, String> errorsMap = ControllerUtils.getErrors(bindingResult);


            throw new BadRequestException("" + errorsMap);
        } else {
            goodService.save(good);
            log.debug("ADMIN added product to DB: id={}, perfumer={}, good={}",
                    good.getId(), good.getProducer(), good.getTitle());
        }

        return good;
    }

    /**
     * Returns all users.
     * The @PreAuthorize annotation says the controller is accessible
     * only to users with administrator rights.
     * URL request {"/user"}, method GET.
     *
     * @return userList.
     */
    @PreAuthorize("hasAuthority('ADMIN', 'OWNER')")
    @GetMapping
    public List<User> userList() {
        return userService.findAll();
    }

    /**
     * Save edited user by an administrator.
     * The @PreAuthorize annotation says the controller is accessible
     * only to users with administrator rights.
     * URL request {"/user"}, method POST.
     *
     * @param username user name.
     * @param form     user roles.
     * @param user     user id.
     * @return user".
     */
    @PreAuthorize("hasAuthority('ADMIN', 'OWNER')")
    @PostMapping
    public User userSaveEditForm(
            @RequestParam String username,
            @RequestParam Map<String, String> form,
            @RequestParam("userId") User user
    ) {
        userService.userSave(username, form, user);

        log.debug("ADMIN save edited user form: id={}, name={}, form={}", user.getId(), username, form);

        return user;
    }


    /**
     * Save edited password or email to the database by user.
     * URL request {"/user/edit"}, method POST.
     *
     * @param user     request Authenticated user.
     * @param password password to change.
     * @param email    email to change.
     * @return user.
     */
    @PostMapping("edit")
    public User updateProfileInfo(
            @AuthenticationPrincipal User user,
            @RequestParam String password,
            @RequestParam String email
    ) {
        userService.updateProfile(user, password, email);

        log.debug("{} change personal info: password={}, email={}", user.getUsername(), password, email);

        return user;
    }
}