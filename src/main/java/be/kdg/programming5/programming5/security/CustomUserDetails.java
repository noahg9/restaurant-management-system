package be.kdg.programming5.programming5.security;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;

import java.util.Collection;

public class CustomUserDetails extends User {
    private final long chefId;

    public CustomUserDetails(String username, String password,
                             Collection<? extends GrantedAuthority> authorities,
                             long chefId) {
        super(username, password, authorities);
        this.chefId = chefId;
    }

    public long getChefId() {
        return chefId;
    }
}
