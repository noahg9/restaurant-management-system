package be.kdg.programming5.programming5.security;

import be.kdg.programming5.programming5.service.ChefService;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class CustomUserDetailsService implements UserDetailsService {
    private final ChefService chefService;

    public CustomUserDetailsService(ChefService chefService) {
        this.chefService = chefService;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        var chef = chefService.getChefByName(username);
        if (chef != null) {
            var authorities = new ArrayList<SimpleGrantedAuthority>();
            authorities.add(new SimpleGrantedAuthority(chef.getRole().getCode()));
            // Spring's `User`
            return new CustomUserDetails(
                    chef.getUsername(),
                    chef.getPassword(),
                    authorities,
                    chef.getId());
        }
        throw new UsernameNotFoundException("User " + username + " was not found.");
    }
}