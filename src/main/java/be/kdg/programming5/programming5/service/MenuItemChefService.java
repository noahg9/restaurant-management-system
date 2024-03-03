package be.kdg.programming5.programming5.service;

import be.kdg.programming5.programming5.domain.Chef;
import be.kdg.programming5.programming5.domain.MenuItem;
import be.kdg.programming5.programming5.repository.MenuItemChefRepository;
import org.springframework.stereotype.Service;

@Service
public class MenuItemChefService {
    private final MenuItemChefRepository menuItemChefRepository;

    public MenuItemChefService(MenuItemChefRepository menuItemChefRepository) {
        this.menuItemChefRepository = menuItemChefRepository;
    }

    public void removeAllChefs(Chef chef) {
        menuItemChefRepository.deleteAll(chef.getMenuItems());
    }

    public void removeAllMenuItems(MenuItem menuItem) {
        menuItemChefRepository.deleteAll(menuItem.getChefs());
    }
}
