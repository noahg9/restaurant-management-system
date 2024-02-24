package be.kdg.programming5.programming5.repository;

import be.kdg.programming5.programming5.domain.Chef;
import be.kdg.programming5.programming5.domain.MenuItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

/**
 * Spring Data repository for accessing Chef entities.
 * Provides methods to interact with Chef data stored in a database.
 */
@Repository
public interface ChefRepository extends JpaRepository<Chef, Integer> {

    @Query("""
        select c from Chef c
        left join fetch c.menuItems m
        left join fetch c.menuItems
        where c.id = :id
        """)
    Optional<Chef> findByIdWithMenuItems(@Param("id") int id);

    @Query("""
        select m from MenuItem m
        left join fetch m.chefs c
        left join fetch c.menuItem
        """)
    List<Chef> findAllWithMenuItems();

    @Query("""
           select c from Chef c
           left join c.menuItems menuItems
           left join menuItems.menuItem m
           where m.id = :menuItemId
           """)
    List<Chef> findByMenuItemId(int menuItemId);

    /**
     * Finds chefs by either first name or last name, ignoring case.
     *
     * @param firstName The first name to search for.
     * @param lastName  The last name to search for.
     * @return A list of chefs matching the specified first name or last name.
     */
    List<Chef> findByFirstNameIgnoreCaseContainingOrLastNameIgnoreCaseContaining(String firstName, String lastName);

    List<Chef> findChefsByNameLike(String searchTerm);
}
