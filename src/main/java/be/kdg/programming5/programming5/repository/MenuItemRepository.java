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
        select m from MenuItem m
        left join fetch m.chefs c
        left join fetch m.chefs
        where m.id = :id
        """)
    Optional<MenuItem> findByIdWithChefs(@Param("id") int id);

    @Query("""
        select c from Chef c
        left join fetch c.menuItems m
        left join fetch m.chef
        """)
    List<MenuItem> findAllWithChefs();

    @Query("""
           select m from MenuItem m
           left join m.chefs chefs
           left join chefs.chef c
           where c.id = :chefId
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
