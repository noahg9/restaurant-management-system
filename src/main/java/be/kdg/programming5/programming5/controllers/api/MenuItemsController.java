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
@RequestMapping("/api/menu-items")
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

    // "/api/menu-items/{id}"
    @GetMapping("{id}")
    ResponseEntity<MenuItemDto> getOneMenuItem(@PathVariable("id") int menuItemId) {
        var menuItem = menuItemService.getMenuItem(menuItemId);
        if (menuItem == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return ResponseEntity.ok(modelMapper.map(menuItem, MenuItemDto.class));
    }

    // "/api/menu-items/{id}/chefs"
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

    // "/api/menu-items"
    @GetMapping
    ResponseEntity<List<MenuItemDto>> searchMenuItems(@RequestParam(required = false) String search) {
        if (search == null) {
            return ResponseEntity
                    .ok(menuItemService.getAllMenuItems()
                            .stream()
                            .map(menuItem -> modelMapper.map(menuItem, MenuItemDto.class))
                            .toList());
        } else {
            var searchResult = menuItemService.searchMenuItemsByNameLike(search);
            if (searchResult.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            } else {
                return ResponseEntity.ok(searchResult
                        .stream()
                        .map(menuItem -> modelMapper.map(menuItem, MenuItemDto.class))
                        .toList());            }
        }
    }

    // "/api/menu-items/{id}"
    @DeleteMapping("{id}")
    ResponseEntity<Void> deleteMenuItem(@PathVariable("id") int menuItemId) {
        if (menuItemService.removeMenuItem(menuItemId)) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
}
