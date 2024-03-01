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
public interface ChefRepository extends JpaRepository<Chef, Integer> {

    @Query("""
        select chef from Chef chef
        left join fetch chef.menuItems menuItems
        left join fetch menuItems.menuItem
        where chef.id = :chefId
        """)
    Optional<Chef> findByIdWithMenuItems(int chefId);

    @Query("""
        select chef from Chef chef
        left join fetch chef.menuItems menuItemChef
        left join fetch menuItemChef.menuItem
        """)
    List<Chef> findAllWithMenuItems();

    @Query("""
           select chef from Chef chef
           left join chef.menuItems menuItemChefs
           left join menuItemChefs.menuItem menuItem
           where menuItem.id = :menuItemId
           """)
    List<Chef> findByMenuItemId(int menuItemId);

    /**
     * Finds chefs by either first name or last name, ignoring case.
     *
     * @param firstName The first name to search for.
     * @param lastName  The last name to search for.
     * @return A all_chefs of chefs matching the specified first name or last name.
     */
    List<Chef> findByFirstNameIgnoreCaseContainingOrLastNameIgnoreCaseContaining(String firstName, String lastName);

    List<Chef> findChefsByFirstNameLikeOrLastNameLike(String searchTerm1, String searchTerm2);
}
