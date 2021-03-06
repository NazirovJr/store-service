package com.liga.store.controller;

import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;

import java.util.*;
import java.util.stream.Collector;
import java.util.stream.Collectors;

/**
 * Class with utility methods for controller classes.
 *
 * @author Nazirov Ilhomnjon (naziroffjr@gmail.com)
 * @version 1.0
 */
public class ControllerUtils {
    /**
     * Returns validation errors to html page.
     *
     * @param bindingResult errors in validating http request.
     * @return validation errors to html page.
     */
    static Map<String, String> getErrors(BindingResult bindingResult) {
        Collector<FieldError, ?, Map<String, String>> collector = Collectors.toMap(
                fieldError -> fieldError.getField() + "Error",
                FieldError::getDefaultMessage
        );
        return bindingResult.getFieldErrors().stream().collect(collector);
    }


}
