package be.kdg.programming5.programming5.repository;

import be.kdg.programming5.programming5.domain.Chef;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

/**
 * Spring Data repository for accessing Chef entities.
 * Provides methods to interact with Chef data stored in a database.
 */
@Repository
public interface ChefRepository extends JpaRepository<Chef, Long> {

    @Query("""
        select c from Chef c
        left join fetch c.menuItems menuItems
        left join fetch menuItems.menuItem
        where c.id = :chefId
        """)
    Optional<Chef> findByIdWithMenuItems(long chefId);

    @Query("""
        select c from Chef c
        left join fetch c.menuItems menuItems
        left join fetch menuItems.menuItem
        """)
    List<Chef> findAllWithMenuItems();

    @Query("""
           select c from Chef c
           left join c.menuItems menuItems
           left join menuItems.menuItem menuItem
           where menuItem.id = :menuItemId
           """)
    List<Chef> findByMenuItemId(long menuItemId);

    /**
     * Finds chefs by either first name or last name, ignoring case.
     *
     * @param firstName The first name to search for.
     * @param lastName  The last name to search for.
     * @return All chefs of chefs matching the specified first name or last name.
     */
    List<Chef> findByFirstNameIgnoreCaseContainingOrLastNameIgnoreCaseContaining(String firstName, String lastName);

    List<Chef> getChefsByFirstNameLikeIgnoreCaseOrLastNameIgnoreCase(String searchTerm1, String searchTerm2);
}
