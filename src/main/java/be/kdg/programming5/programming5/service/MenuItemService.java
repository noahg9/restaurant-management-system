package be.kdg.programming5.programming5.service;

import be.kdg.programming5.programming5.domain.*;
import be.kdg.programming5.programming5.repository.MenuAssignmentRepository;
import be.kdg.programming5.programming5.repository.ChefRepository;
import be.kdg.programming5.programming5.repository.MenuItemRepository;
import be.kdg.programming5.programming5.repository.RecipeRepository;
import jakarta.transaction.Transactional;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.io.InputStream;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Scanner;
import java.util.logging.Logger;

/**
 * The type Menu item service.
 */
@Service
public class MenuItemService {
    private static final Logger LOGGER = Logger.getLogger(MenuItemService.class.getName());

    private final MenuItemRepository menuItemRepository;
    private final MenuAssignmentRepository menuAssignmentRepository;
    private final ChefRepository chefRepository;
    private final RecipeRepository recipeRepository;

    /**
     * Instantiates a new Menu item service.
     *
     * @param menuItemRepository     the menu item repository
     * @param menuAssignmentRepository the assigned chef repository
     * @param chefRepository         the chef repository
     * @param recipeRepository       the recipe repository
     */
    public MenuItemService(MenuItemRepository menuItemRepository, MenuAssignmentRepository menuAssignmentRepository, ChefRepository chefRepository, RecipeRepository recipeRepository) {
        this.menuItemRepository = menuItemRepository;
        this.menuAssignmentRepository = menuAssignmentRepository;
        this.chefRepository = chefRepository;
        this.recipeRepository = recipeRepository;
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
    @Transactional
    public MenuItem getMenuItemWithChefs(long menuItemId) {
        return menuItemRepository.findByIdWithChefs(menuItemId)
                .map(menuItem -> {
                    menuItem.getChefs().size();
                    return menuItem;
                })
                .orElse(null);
    }

    /**
     * Search menu items by name like list.
     *
     * @param searchTerm the search term
     * @return the list
     */
    @Cacheable("search-menu-items")
    public List<MenuItem> searchMenuItemsByNameLike(String searchTerm) {
        return menuItemRepository.findMenuItemsByNameLikeIgnoreCase("%" + searchTerm + "%");
    }

    /**
     * Save menu item menu item.
     *
     * @param name       the name
     * @param price      the price
     * @param course     the course
     * @param vegetarian the vegetarian
     * @param spiceLevel   the spice level
     * @param chefId     the chef id
     * @return the menu item
     */
    @Transactional
    @CacheEvict(value = "search-menu-items", allEntries = true)
    public MenuItem saveMenuItem(String name, double price, Course course, Boolean vegetarian, int spiceLevel, List<Long> chefIds, Long chefId) {
        Logger logger = Logger.getLogger(this.getClass().getName());

        MenuItem menuItem = menuItemRepository.save(new MenuItem(name, price, course, vegetarian, spiceLevel));

        // Handle the single chefId if provided
        if (chefId != null) {
            Chef chef = chefRepository.findById(chefId).orElse(null);
            if (chef != null) {
                menuAssignmentRepository.save(new MenuAssignment(menuItem, chef, LocalDateTime.now()));
                logger.info("Single chefId associated: " + chefId);
            } else {
                logger.warning("Chef with ID " + chefId + " not found.");
            }
        }

        // Handle the list of chefIds if provided
        if (chefIds != null && !chefIds.isEmpty()) {
            logger.info("Processing list of chefIds: " + chefIds);
            for (Long associatedChefId : chefIds) {
                logger.info("Processing chefId: " + associatedChefId);
                chefRepository.findById(associatedChefId).ifPresentOrElse(chef -> {
                    menuAssignmentRepository.save(new MenuAssignment(menuItem, chef, LocalDateTime.now()));
                    logger.info("ChefId from list associated: " + associatedChefId);
                }, () -> {
                    logger.warning("Chef with ID " + associatedChefId + " not found.");
                });
            }
        } else {
            logger.info("No additional chefIds provided.");
        }

        // Save a new recipe and associate it with the MenuItem
        Recipe newRecipe = recipeRepository.save(new Recipe("", 0, 0));
        newRecipe.setMenuItem(menuItem);

        return menuItem;
    }

    /**
     * Update menu item boolean.
     *
     * @param menuItemId the menu item id
     * @param name       the name
     * @param price      the price
     * @param vegetarian the vegetarian
     * @param spiceLevel   the spice level
     * @return the boolean
     */
    @CacheEvict(value = "search-menu-items", allEntries = true)
    public boolean updateMenuItem(long menuItemId, String name, double price, boolean vegetarian, int spiceLevel) {
        MenuItem menuItem = menuItemRepository.findById(menuItemId).orElse(null);
        if (menuItem == null) {
            return false;
        }
        menuItem.setName(name);
        menuItem.setPrice(price);
        menuItem.setVegetarian(vegetarian);
        menuItem.setSpiceLevel(spiceLevel);
        menuItemRepository.save(menuItem);
        return true;
    }

    /**
     * Delete menu item boolean.
     *
     * @param menuItemId the menu item id
     * @return the boolean
     */
    @Transactional
    @CacheEvict(value = "search-menu-items", allEntries = true)
    public boolean deleteMenuItem(long menuItemId) {
        MenuItem menuItem = menuItemRepository.findByIdWithChefs(menuItemId).orElse(null);
        if (menuItem == null) {
            return false;
        }
        menuAssignmentRepository.deleteAll(menuItem.getChefs());
        menuItemRepository.deleteById(menuItemId);
        return true;
    }

    @Async
    @CacheEvict(value = "search-menu-items", allEntries = true)
    public void processMenuItemsCsv(InputStream inputStream) {
        Scanner scanner = new Scanner(inputStream);
        while (scanner.hasNextLine()) {
            String line = scanner.nextLine();
            var columns = line.split(",");
            String name = columns[0].trim();
            double price = Double.parseDouble(columns[1].trim());
            Course course = Course.valueOf(columns[2].trim());
            boolean vegetarian = Boolean.parseBoolean(columns[3].trim());
            int spiceLevel = Integer.parseInt(columns[4].trim());
            menuItemRepository.save(new MenuItem(name, price, course, vegetarian, spiceLevel));
        }
        scanner.close(); // Close the scanner when done reading the input stream
    }
}
