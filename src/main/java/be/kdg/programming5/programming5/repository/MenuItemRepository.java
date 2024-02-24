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
 * Spring Data repository for accessing MenuItem entities.
 * Provides methods to interact with MenuItem data stored in a database.
 */
@Repository
public interface MenuItemRepository extends JpaRepository<MenuItem, Integer> {

    @Query("""
        select menuItem from MenuItem menuItem
        left join fetch menuItem.chefs menuItemChef
        left join fetch menuItemChef.chef
        where menuItem.id = :id
        """)
    Optional<MenuItem> findByIdWithChefs(@Param("id") int id);

    @Query("""
        select chef from Chef chef
        left join fetch chef.menuItems menuItemChef
        left join fetch menuItemChef.chef
        """)
    List<MenuItem> findAllWithChefs();

    @Query("""
           select menuItem from MenuItem menuItem
           left join menuItem.chefs menuItemChef
           left join menuItemChef.chef chef
           where chef.id = :chefId
           """)
    List<MenuItem> findByChefId(int chefId);

    /**
     * Finds menu items with a price less than or equal to the specified maximum price.
     *
     * @param maxPrice The maximum price to search for.
     * @return A list of menu items with a price less than or equal to the specified maximum price.
     */
    List<MenuItem> findByPriceLessThanEqual(double maxPrice);

    /**
     * Finds vegetarian menu items.
     *
     * @return A list of vegetarian menu items.
     */
    List<MenuItem> findByVegetarianTrue();

    List<MenuItem> findMenuItemsByNameLike(String searchTerm);

}
