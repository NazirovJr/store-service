package com.liga.store.utils;

import com.liga.store.domain.User;
import org.springframework.security.core.context.SecurityContextHolder;

public class Utils {

    private Utils() {}

    public static User getAuthenticatedUser() {
        return ((User) SecurityContextHolder.getContext()
                .getAuthentication()
                .getPrincipal());
    }
}
