package be.kdg.programming5.programming5.controllers.api;

import be.kdg.programming5.programming5.controllers.api.dto.ChefDto;
import be.kdg.programming5.programming5.controllers.api.dto.MenuItemDto;
import be.kdg.programming5.programming5.domain.Chef;
import be.kdg.programming5.programming5.domain.MenuItem;
import be.kdg.programming5.programming5.service.ChefService;
import be.kdg.programming5.programming5.service.MenuItemService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/chefs")
public class ChefsController {
    private final ChefService chefService;
    private final MenuItemService menuItemService;

    public ChefsController(ChefService chefService, MenuItemService menuItemService) {
        this.chefService = chefService;
        this.menuItemService = menuItemService;
    }

    @GetMapping("{id}") // "/api/chefs/{id}"
    Chef getOneChef(@PathVariable("id") int chefId) {
        return chefService.getChef(chefId);
    }

    @GetMapping("{id}/menuItems")  // "/api/chefs/1/menuItems"
    public List<MenuItemDto> getMenuItemsOfChef(@PathVariable("id") int chefId) {
        return menuItemService
                .getMenuItemsOfChef(chefId)
                .stream()
                .map(menuItem -> new MenuItemDto(menuItem.getId(), menuItem.getName(), menuItem.getPrice(), menuItem.getCourse(), menuItem.getVegetarian(), menuItem.getSpiceLvl()))
                .toList();
    }

    @GetMapping // "/api/chefs"
    ResponseEntity<List<Chef>> searchChefs(@RequestParam(required = false) String search) {
        // TODO: return menuItemDto
        if (search == null) {
            return ResponseEntity.ok(chefService.getChefs());
        } else {
            var searchResult = chefService.searchChefsByFirstNameLikeOrLastNameLike(search, search);
            if (searchResult.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            } else {
                return ResponseEntity.ok(searchResult);
            }
        }
    }

    @DeleteMapping("{id}")
    ResponseEntity<Void> deleteChef(@PathVariable("id") int chefId) {
        // TODO: 204 ... AND(!) ... 404
        // TODO: chefService.delete(chefId)
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
