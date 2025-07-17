package com.noahg9.restaurant.domain;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;

import java.util.Collection;

/**
 * The type Custom user details.
 */
public class CustomUserDetails extends User {
    private final long chefId;

    /**
     * Instantiates a new Custom user details.
     *
     * @param username    the username
     * @param password    the password
     * @param authorities the authorities
     * @param chefId      the chef id
     */
    public CustomUserDetails(String username,
                             String password,
                             Collection<? extends GrantedAuthority> authorities,
                             long chefId) {
        super(username, password, authorities);
        this.chefId = chefId;
    }

    /**
     * Gets chef id.
     *
     * @return the chef id
     */
    public long getChefId() {
        return chefId;
    }
}
