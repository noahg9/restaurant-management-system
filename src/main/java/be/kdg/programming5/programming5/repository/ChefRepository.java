package be.kdg.programming5.programming5.repository;

import be.kdg.programming5.programming5.domain.Chef;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

/**
 * The interface Chef repository.
 */
public interface ChefRepository extends JpaRepository<Chef, Long> {

    /**
     * Find by id with menu items optional.
     *
     * @param chefId the chef id
     * @return the optional
     */
    @Query("""
        select c from Chef c
        left join fetch c.menuItems menuItems
        left join fetch menuItems.menuItem
        where c.id = :chefId
        """)
    Optional<Chef> findByIdWithMenuItems(long chefId);

    /**
     * Find by username optional.
     *
     * @param username the username
     * @return the optional
     */
    Optional<Chef> findByUsername(String username);

    /**
     * Find all with menu items list.
     *
     * @return the list
     */
    @Query("""
        select c from Chef c
        left join fetch c.menuItems menuItems
        left join fetch menuItems.menuItem
        """)
    List<Chef> findAllWithMenuItems();

    /**
     * Find by menu item id list.
     *
     * @param menuItemId the menu item id
     * @return the list
     */
    @Query("""
           select c from Chef c
           left join c.menuItems menuItems
           left join menuItems.menuItem menuItem
           where menuItem.id = :menuItemId
           """)
    List<Chef> findByMenuItemId(long menuItemId);

    /**
     * Find by first name ignore case containing or last name ignore case containing list.
     *
     * @param firstName the first name
     * @param lastName  the last name
     * @return the list
     */
    List<Chef> findByFirstNameIgnoreCaseContainingOrLastNameIgnoreCaseContaining(String firstName, String lastName);

    /**
     * Gets chefs by first name like ignore case or last name ignore case.
     *
     * @param searchTerm1 the search term 1
     * @param searchTerm2 the search term 2
     * @return the chefs by first name like ignore case or last name ignore case
     */
    List<Chef> getChefsByFirstNameLikeIgnoreCaseOrLastNameIgnoreCase(String searchTerm1, String searchTerm2);
}
