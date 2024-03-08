package be.kdg.programming5.programming5.service;

import be.kdg.programming5.programming5.domain.User;
import be.kdg.programming5.programming5.repository.UserRepository;
import org.springframework.stereotype.Service;

/**
 * The type User service.
 */
@Service
public class UserService {
    private final UserRepository userRepository;

    /**
     * Instantiates a new User service.
     *
     * @param userRepository the user repository
     */
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    /**
     * Gets user by name.
     *
     * @param username the username
     * @return the user by name
     */
    public User getUserByName(String username) {
        return userRepository.findByUsername(username);
    }
}
