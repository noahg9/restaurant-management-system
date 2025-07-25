package com.noahg9.restaurant.controller.api;

import com.noahg9.restaurant.domain.Course;
import com.noahg9.restaurant.domain.CustomUserDetails;
import com.noahg9.restaurant.domain.MenuAssignment;
import com.noahg9.restaurant.domain.MenuItem;
import com.noahg9.restaurant.dto.ChefDto;
import com.noahg9.restaurant.dto.MenuItemDto;
import com.noahg9.restaurant.dto.NewMenuItemDto;
import com.noahg9.restaurant.dto.UpdateMenuItemDto;
import com.noahg9.restaurant.service.MenuAssignmentService;
import com.noahg9.restaurant.service.MenuItemService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

import static com.noahg9.restaurant.domain.ChefRole.HEAD_CHEF;


/**
 * The type Menu items controller.
 */
@RestController
@RequestMapping("/api/menu-items")
public class MenuItemsController {
    private final MenuItemService menuItemService;
    private final MenuAssignmentService menuAssignmentService;
    private final ModelMapper modelMapper;

    /**
     * Instantiates a new Menu items controller.
     *
     * @param menuItemService       the menu item service
     * @param menuAssignmentService the menu assignment service
     * @param modelMapper           the model mapper
     */
    public MenuItemsController(MenuItemService menuItemService, MenuAssignmentService menuAssignmentService, ModelMapper modelMapper) {
        this.menuItemService = menuItemService;
        this.menuAssignmentService = menuAssignmentService;
        this.modelMapper = modelMapper;
    }

    /**
     * Gets menu item.
     *
     * @param menuItemId the menu item id
     * @return the menu item
     */
    @GetMapping("{id}")
    ResponseEntity<MenuItemDto> getMenuItem(@PathVariable("id") long menuItemId) {
        MenuItem menuItem = menuItemService.getMenuItem(menuItemId);
        if (menuItem == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return ResponseEntity.ok(modelMapper.map(menuItem, MenuItemDto.class));
    }

    /**
     * Gets all menu items.
     *
     * @return the all menu items
     */
    @GetMapping
    ResponseEntity<List<MenuItemDto>> getAllMenuItems() {
        List<MenuItem> allMenuItems = menuItemService.getAllMenuItems();
        if (allMenuItems.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } else {
            List<MenuItemDto> menuItemDtos = allMenuItems.stream().map(menuItem -> modelMapper.map(menuItem, MenuItemDto.class)).collect(Collectors.toList());
            return ResponseEntity.ok(menuItemDtos);
        }
    }

    /**
     * Gets chefs of menu item.
     *
     * @param menuItemId the menu item id
     * @return the chefs of menu item
     */
    @GetMapping("{id}/chefs")
    ResponseEntity<List<ChefDto>> getChefsOfMenuItem(@PathVariable("id") long menuItemId) {
        MenuItem menuItem = menuItemService.getMenuItemWithChefs(menuItemId);
        if (menuItem == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        if (menuItem.getChefs().isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return ResponseEntity.ok(menuItem.getChefs().stream().map(MenuAssignment::getChef).map(chef -> modelMapper.map(chef, ChefDto.class)).toList());
    }

    /**
     * Search menu items response entity.
     *
     * @param search the search
     * @return the response entity
     */
    @GetMapping("search")
    ResponseEntity<List<MenuItemDto>> searchMenuItems(@RequestParam(required = false) String search) {
        if (search == null || search.trim().isEmpty()) {
            return ResponseEntity.ok(menuItemService.getAllMenuItems().stream().map(menuItem -> modelMapper.map(menuItem, MenuItemDto.class)).toList());
        } else {
            List<MenuItem> searchResult = menuItemService.searchMenuItemsByNameLike(search);
            if (searchResult.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            } else {
                return ResponseEntity.ok(searchResult.stream().map(menuItem -> modelMapper.map(menuItem, MenuItemDto.class)).toList());
            }
        }
    }

    /**
     * Create menu item response entity.
     *
     * @param menuItemDto the menu item dto
     * @param user        the user
     * @return the response entity
     */
    @PostMapping
    ResponseEntity<MenuItemDto> createMenuItem(@RequestBody @Valid NewMenuItemDto menuItemDto, @AuthenticationPrincipal CustomUserDetails user) {
        MenuItem createdMenuItem = menuItemService.saveMenuItem(
                menuItemDto.getName(), menuItemDto.getPrice(), Course.fromName(menuItemDto.getCourseName()), menuItemDto.isVegetarian(), menuItemDto.getSpiceLevel(), user == null ? null : user.getChefId());
        return new ResponseEntity<>(modelMapper.map(createdMenuItem, MenuItemDto.class), HttpStatus.CREATED);
    }

    /**
     * Update menu item response entity.
     *
     * @param menuItemId        the menu item id
     * @param updateMenuItemDto the update menu item dto
     * @param user              the user
     * @param request           the request
     * @return the response entity
     */
    @PatchMapping("{id}")
    ResponseEntity<Void> updateMenuItem(@PathVariable("id") long menuItemId, @RequestBody @Valid UpdateMenuItemDto updateMenuItemDto, @AuthenticationPrincipal CustomUserDetails user, HttpServletRequest request) {
        if (!menuAssignmentService.isChefAssignedToMenuItem(menuItemId, user.getChefId()) && !request.isUserInRole(HEAD_CHEF.getCode())) {
            return new ResponseEntity<>(HttpStatus.FORBIDDEN);
        }
        if (menuItemService.updateMenuItem(menuItemId, updateMenuItemDto.getName(), updateMenuItemDto.getPrice(), updateMenuItemDto.isVegetarian(), updateMenuItemDto.getSpiceLevel())) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    /**
     * Delete menu item response entity.
     *
     * @param menuItemId the menu item id
     * @param user       the user
     * @param request    the request
     * @return the response entity
     */
    @DeleteMapping("{id}")
    ResponseEntity<Void> deleteMenuItem(@PathVariable("id") long menuItemId, @AuthenticationPrincipal CustomUserDetails user, HttpServletRequest request) {
        if (!menuAssignmentService.isChefAssignedToMenuItem(menuItemId, user.getChefId()) && !request.isUserInRole(HEAD_CHEF.getCode())) {
            return new ResponseEntity<>(HttpStatus.FORBIDDEN);
        }
        if (menuItemService.deleteMenuItem(menuItemId)) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
}
