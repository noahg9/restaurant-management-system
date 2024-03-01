package be.kdg.programming5.programming5.controllers.api;

import be.kdg.programming5.programming5.controllers.api.dto.ChefDto;
import be.kdg.programming5.programming5.controllers.api.dto.MenuItemDto;
import be.kdg.programming5.programming5.controllers.api.dto.NewMenuItemDto;
import be.kdg.programming5.programming5.domain.MenuItem;
import be.kdg.programming5.programming5.domain.MenuItemChef;
import be.kdg.programming5.programming5.service.MenuItemService;
import jakarta.validation.Valid;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/menuitems")
public class MenuItemsController {
    private final MenuItemService menuItemService;
    private final ModelMapper modelMapper;

    public MenuItemsController(MenuItemService menuItemService, ModelMapper modelMapper) {
        this.menuItemService = menuItemService;
        this.modelMapper = modelMapper;
    }

    @PostMapping
    ResponseEntity<MenuItemDto> addIssue(@RequestBody @Valid NewMenuItemDto menuItemDto) {
        var createdMenuItem = menuItemService.addMenuItem(menuItemDto.getName(), menuItemDto.getPrice(), menuItemDto.getCourse(), menuItemDto.isVegetarian(), menuItemDto.getSpiceLvl(), menuItemDto.getRestaurant());
        return new ResponseEntity<>(
                modelMapper.map(createdMenuItem, MenuItemDto.class),
                HttpStatus.CREATED
        );
    }

    // "/api/menuitems/{id}"
    @GetMapping("{id}")
    MenuItem getOneMenuItem(@PathVariable("id") int menuItemId) {
        return menuItemService.getMenuItem(menuItemId);
    }

    // "/api/menuitems/{id}/chefs"
    @GetMapping("{id}/chefs")
    ResponseEntity<List<ChefDto>> getChefsOfMenuItem(@PathVariable("id") int menuItemId) {
        var menuItem = menuItemService.getMenuItemWithChefs(menuItemId);
        if (menuItem == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        if (menuItem.getChefs().isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return ResponseEntity.ok(menuItem.getChefs()
                .stream()
                .map(MenuItemChef::getChef)
                .map(dev -> modelMapper.map(dev, ChefDto.class))
                .toList());
    }

    // "/api/menuitems"
    @GetMapping
    ResponseEntity<List<MenuItem>> searchMenuItems(@RequestParam(required = false) String search) {
        // TODO: return menuItemDto
        if (search == null) {
            return ResponseEntity.ok(menuItemService.getAllMenuItems());
        } else {
            var searchResult = menuItemService.searchMenuItemsByNameLike(search);
            if (searchResult.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            } else {
                return ResponseEntity.ok(searchResult);
            }
        }
    }

    // "/api/menuitems/{id}"
    @DeleteMapping("{id}")
    ResponseEntity<Void> deleteMenuItem(@PathVariable("id") int menuItemId) {
        if (menuItemService.removeMenuItem(menuItemId)) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
}
