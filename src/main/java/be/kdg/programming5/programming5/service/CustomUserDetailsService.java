package be.kdg.programming5.programming5.service;

import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

/**
 * The type Custom user details service.
 */
@Service
public class CustomUserDetailsService implements UserDetailsService {
    private final UserService userService;

    /**
     * Instantiates a new Custom user details service.
     *
     * @param userService the user service
     */
    public CustomUserDetailsService(UserService userService) {
        this.userService = userService;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        var user = userService.getUserByName(username);
        if (user != null) {
            var authorities = new ArrayList<SimpleGrantedAuthority>();
            return new User(user.getUsername(), user.getPassword(), authorities);
        }
        throw new UsernameNotFoundException("User '" + username + "' doesn't exist");
    }
}
