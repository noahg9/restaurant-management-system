package be.kdg.programming5.programming5.service;

import be.kdg.programming5.programming5.model.AssignedChef;
import be.kdg.programming5.programming5.model.Chef;
import be.kdg.programming5.programming5.model.Course;
import be.kdg.programming5.programming5.model.MenuItem;
import be.kdg.programming5.programming5.repository.AssignedChefRepository;
import be.kdg.programming5.programming5.repository.ChefRepository;
import be.kdg.programming5.programming5.repository.MenuItemRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

/**
 * The type Menu item service.
 */
@Service
public class MenuItemService {
    private final MenuItemRepository menuItemRepository;
    private final AssignedChefService assignedChefService;
    private final AssignedChefRepository assignedChefRepository;
    private final ChefRepository chefRepository;

    /**
     * Instantiates a new Menu item service.
     *
     * @param menuItemRepository     the menu item repository
     * @param assignedChefService    the menu item chef service
     * @param assignedChefRepository the menu item chef repository
     * @param chefRepository         the chef repository
     */
    public MenuItemService(MenuItemRepository menuItemRepository, AssignedChefService assignedChefService, AssignedChefRepository assignedChefRepository, ChefRepository chefRepository) {
        this.menuItemRepository = menuItemRepository;
        this.assignedChefService = assignedChefService;
        this.assignedChefRepository = assignedChefRepository;
        this.chefRepository = chefRepository;
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
     * @param userId     the user id
     * @return the menu item
     */
    public MenuItem addMenuItem(String name, double price, Course course, Boolean vegetarian, int spiceLvl, long userId) {
        MenuItem menuItem = menuItemRepository.save(new MenuItem(name, price, course, vegetarian, spiceLvl));
        MenuItem savedMenuItem = menuItemRepository.save(menuItem);
        Chef chef = chefRepository.findById(userId).orElse(null);
        assignedChefRepository.save(new AssignedChef(savedMenuItem, chef, LocalDateTime.now()));
        return savedMenuItem;
    }

    /**
     * Remove menu item boolean.
     *
     * @param menuItemId the menu item id
     * @return the boolean
     */
    @Transactional
    public boolean removeMenuItem(long menuItemId) {
        Optional<MenuItem> menuItem = menuItemRepository.findByIdWithChefs(menuItemId);
        if (menuItem.isEmpty()) {
            return false;
        }
        assignedChefService.removeAllMenuItems(menuItem.get());
        menuItemRepository.deleteById(menuItemId);
        return true;
    }

    /**
     * Change menu item boolean.
     *
     * @param menuItemId the menu item id
     * @param name       the name
     * @param price      the price
     * @param vegetarian the vegetarian
     * @param spiceLvl   the spice lvl
     * @return the boolean
     */
    public boolean changeMenuItem(long menuItemId, String name, double price, boolean vegetarian, int spiceLvl) {
        MenuItem menuItem = menuItemRepository.findById(menuItemId).orElse(null);
        if (menuItem == null) {
            return false;
        }
        menuItem.setName(name);
        menuItem.setPrice(price);
        menuItem.setVegetarian(vegetarian);
        menuItem.setSpiceLvl(spiceLvl);
        menuItemRepository.save(menuItem);
        return true;
    }
}
