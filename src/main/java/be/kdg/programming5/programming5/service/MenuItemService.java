package be.kdg.programming5.programming5.service;

import be.kdg.programming5.programming5.domain.Course;
import be.kdg.programming5.programming5.domain.MenuItem;
import be.kdg.programming5.programming5.domain.Restaurant;
import be.kdg.programming5.programming5.repository.MenuItemRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * The type Menu item service.
 */
@Service
public class MenuItemService {
    private final MenuItemRepository menuItemRepository;
    private final MenuItemChefService menuItemChefService;
    private final RestaurantService restaurantService;

    /**
     * Instantiates a new Menu item service.
     *
     * @param menuItemRepository  the menu item repository
     * @param menuItemChefService the menu item chef service
     * @param restaurantService   the restaurant service
     */
    public MenuItemService(MenuItemRepository menuItemRepository, MenuItemChefService menuItemChefService, RestaurantService restaurantService) {
        this.menuItemRepository = menuItemRepository;
        this.menuItemChefService = menuItemChefService;
        this.restaurantService = restaurantService;
    }

    /**
     * Gets all menu items.
     *
     * @return the all menu items
     */
    public List<MenuItem> getAllMenuItems() {
        return menuItemRepository.findAll();
    }

    /**
     * Gets menu items with chefs.
     *
     * @return the menu items with chefs
     */
    public List<MenuItem> getMenuItemsWithChefs() {
        return menuItemRepository.findAllWithChefs();
    }

    /**
     * Gets menu item.
     *
     * @param menuItemId the menu item id
     * @return the menu item
     */
    public MenuItem getMenuItem(long menuItemId) {
        return menuItemRepository.findById(menuItemId).orElse(null);
    }

    /**
     * Gets menu item with chefs.
     *
     * @param menuItemId the menu item id
     * @return the menu item with chefs
     */
    public MenuItem getMenuItemWithChefs(long menuItemId) {
        return menuItemRepository.findByIdWithChefs(menuItemId).orElse(null);
    }

    /**
     * Gets menu items of chef.
     *
     * @param chefId the chef id
     * @return the menu items of chef
     */
    public List<MenuItem> getMenuItemsOfChef(long chefId) {
        return menuItemRepository.findByChefId(chefId);
    }

    /**
     * Gets menu items by max price.
     *
     * @param maxPrice the max price
     * @return the menu items by max price
     */
    public List<MenuItem> getMenuItemsByMaxPrice(double maxPrice) {
        return menuItemRepository.findByPriceLessThanEqual(maxPrice);
    }

    /**
     * Gets veg menu items.
     *
     * @return the veg menu items
     */
    public List<MenuItem> getVegMenuItems() {
        return menuItemRepository.findByVegetarianTrue();
    }

    /**
     * Search menu items by name like list.
     *
     * @param searchTerm the search term
     * @return the list
     */
    public List<MenuItem> searchMenuItemsByNameLike(String searchTerm) {
        return menuItemRepository.findMenuItemsByNameLikeIgnoreCase("%" + searchTerm + "%");
    }

    /**
     * Add menu item menu item.
     *
     * @param name       the name
     * @param price      the price
     * @param course     the course
     * @param vegetarian the vegetarian
     * @param spiceLvl   the spice lvl
     * @param restaurant the restaurant
     * @return the menu item
     */
    public MenuItem addMenuItem(String name, double price, Course course, Boolean vegetarian, int spiceLvl, Restaurant restaurant) {
        return menuItemRepository.save(new MenuItem(name, price, course, vegetarian, spiceLvl, restaurant));
    }

    /**
     * Add menu item menu item.
     *
     * @param name       the name
     * @param price      the price
     * @param course     the course
     * @param vegetarian the vegetarian
     * @param spiceLvl   the spice lvl
     * @return the menu item
     */
    public MenuItem addMenuItem(String name, double price, Course course, Boolean vegetarian, int spiceLvl) {
        return menuItemRepository.save(new MenuItem(name, price, course, vegetarian, spiceLvl, restaurantService.getRestaurant(1)));
    }

    /**
     * Add menu item menu item.
     *
     * @param menuItem the menu item
     * @return the menu item
     */
    public MenuItem addMenuItem(MenuItem menuItem) {
        return menuItemRepository.save(menuItem);
    }

    /**
     * Remove menu item boolean.
     *
     * @param menuItemId the menu item id
     * @return the boolean
     */
    @Transactional
    public boolean removeMenuItem(long menuItemId) {
        var menuItem = menuItemRepository.findByIdWithChefs(menuItemId);
        if (menuItem.isEmpty()) {
            return false;
        }
        menuItemChefService.removeAllMenuItems(menuItem.get());
        menuItemRepository.deleteById(menuItemId);
        return true;
    }

    /**
     * Change menu item name boolean.
     *
     * @param menuItemId the menu item id
     * @param name       the name
     * @param price      the price
     * @param course     the course
     * @param vegetarian the vegetarian
     * @param spiceLvl   the spice lvl
     * @return the boolean
     */
    public boolean changeMenuItemName(long menuItemId, String name, double price, Course course, boolean vegetarian, int spiceLvl) {
        var menuItem = menuItemRepository.findById(menuItemId).orElse(null);
        if (menuItem == null) {
            return false;
        }
        menuItem.setName(name);
        menuItem.setPrice(price);
        menuItem.setCourse(course);
        menuItem.setVegetarian(vegetarian);
        menuItem.setSpiceLvl(spiceLvl);
        menuItemRepository.save(menuItem);
        return true;
    }
}
