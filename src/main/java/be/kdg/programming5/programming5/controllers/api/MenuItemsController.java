package be.kdg.programming5.programming5.controllers.api;

import be.kdg.programming5.programming5.controllers.api.dto.ChefDto;
import be.kdg.programming5.programming5.domain.MenuItem;
import be.kdg.programming5.programming5.service.ChefService;
import be.kdg.programming5.programming5.service.MenuItemService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/menuitems")
public class MenuItemsController {
    private final MenuItemService menuItemService;
    private final ChefService chefService;

    public MenuItemsController(MenuItemService menuItemService, ChefService chefService) {
        this.menuItemService = menuItemService;
        this.chefService = chefService;
    }

    @GetMapping("{id}") // "/api/menuItems/{id}"
    MenuItem getOneMenuItem(@PathVariable("id") int menuItemId) {
        return menuItemService.getMenuItem(menuItemId);
    }

    @GetMapping("{id}/chefs")  // "/api/menuItems/1/chefs"
    public List<ChefDto> getChefsOfMenuItem(@PathVariable("id") int menuItemId) {
        return chefService
                .getChefsOfMenuItem(menuItemId)
                .stream()
                .map(chef -> new ChefDto(chef.getId(), chef.getFirstName(), chef.getLastName(), chef.getDateOfBirth()))
                .toList();
    }

    @GetMapping // "/api/menuItems"
    ResponseEntity<List<MenuItem>> searchMenuItems(@RequestParam(required = false) String search) {
        // TODO: return menuItemDto
        if (search == null) {
            return ResponseEntity.ok(menuItemService.getMenuItems());
        } else {
            var searchResult = menuItemService.searchMenuItemsByNameLike(search);
            if (searchResult.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            } else {
                return ResponseEntity.ok(searchResult);
            }
        }
    }

    @DeleteMapping("{id}")
    ResponseEntity<Void> deleteMenuItem(@PathVariable("id") int menuItemId) {
        // TODO: 204 ... AND(!) ... 404
        // TODO: menuItemService.delete(menuItemId)
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
