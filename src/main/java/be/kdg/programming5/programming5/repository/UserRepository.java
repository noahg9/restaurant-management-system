package be.kdg.programming5.programming5.repository;

import be.kdg.programming5.programming5.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * The interface User repository.
 */
public interface UserRepository extends JpaRepository<User, Long> {
    /**
     * Find by username user.
     *
     * @param username the username
     * @return the user
     */
    User findByUsername(String username);
}
