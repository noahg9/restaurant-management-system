package be.kdg.programming5.programming5.service;

import be.kdg.programming5.programming5.model.Chef;
import be.kdg.programming5.programming5.model.CustomUserDetails;
import be.kdg.programming5.programming5.repository.ChefRepository;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collection;

/**
 * The type Custom user details service.
 */
@Service
public class CustomUserDetailsService implements UserDetailsService {
    private final ChefRepository chefRepository;

    /**
     * Instantiates a new Custom user details service.
     *
     * @param chefRepository the chef repository
     */
    public CustomUserDetailsService(ChefRepository chefRepository) {
        this.chefRepository = chefRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Chef chef = chefRepository.findByUsername(username).orElse(null);
        if (chef != null) {
            Collection<SimpleGrantedAuthority> authorities = new ArrayList<>();
            authorities.add(new SimpleGrantedAuthority(chef.getRole().getCode()));
            // Spring's `User`
            return new CustomUserDetails(chef.getUsername(), chef.getPassword(), authorities, chef.getId());
        }
        throw new UsernameNotFoundException("User " + username + " was not found.");
    }
}
