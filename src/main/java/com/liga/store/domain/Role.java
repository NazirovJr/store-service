package com.liga.store.domain;

import org.springframework.security.core.GrantedAuthority;

/**
 * Enumeration of possible user roles implements methods of the {@link GrantedAuthority} interface.
 *
 * @author Nazirov Ilhomnjon (naziroffjr@gmail.com)
 * @version 1.0
 * @see GrantedAuthority
 */
public enum Role implements GrantedAuthority {
    /**
     * Site customer role.
     */
    USER,

    /**
     * Site administrator role.
     *
     */
    ADMIN,

    /**
     * Site owner role.
     *
     */
    OWNER;

    @Override
    public String getAuthority() {
        return name();
    }
}