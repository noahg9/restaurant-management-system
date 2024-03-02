package be.kdg.programming5.programming5.controllers.api;

import be.kdg.programming5.programming5.controllers.api.dto.ChefDto;
import be.kdg.programming5.programming5.controllers.api.dto.MenuItemDto;
import be.kdg.programming5.programming5.controllers.api.dto.NewChefDto;
import be.kdg.programming5.programming5.controllers.api.dto.UpdateChefDobDto;
import be.kdg.programming5.programming5.domain.Chef;
import be.kdg.programming5.programming5.domain.MenuItemChef;
import be.kdg.programming5.programming5.service.ChefService;
import jakarta.validation.Valid;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/chefs")
public class ChefsController {
    private final ChefService chefService;
    private final ModelMapper modelMapper;

    public ChefsController(ChefService chefService, ModelMapper modelMapper) {
        this.chefService = chefService;
        this.modelMapper = modelMapper;
    }

    @PostMapping
    ResponseEntity<ChefDto> addChef(@RequestBody @Valid NewChefDto chefDto) {
        var createdChef = chefService.addChef(chefDto.getFirstName(), chefDto.getLastName(), chefDto.getDateOfBirth());
        return new ResponseEntity<>(
                modelMapper.map(createdChef, ChefDto.class),
                HttpStatus.CREATED
        );
    }

    // "/api/chefs/{id}"
    @GetMapping("{id}")
    ResponseEntity<ChefDto> getOneChef(@PathVariable("id") int chefId) {
        var chef = chefService.getChef(chefId);
        if (chef == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return ResponseEntity.ok(modelMapper.map(chef, ChefDto.class));
    }

    // "/api/chefs/{id}/menu-items"
    @GetMapping("{id}/menu-items")
    ResponseEntity<List<MenuItemDto>> getMenuItemsOfChef(@PathVariable("id") int chefId) {
        var chef = chefService.getChefWithMenuItems(chefId);
        if (chef == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        if (chef.getMenuItems().isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return ResponseEntity.ok(chef.getMenuItems()
                .stream()
                .map(MenuItemChef::getMenuItem)
                .map(dev -> modelMapper.map(dev, MenuItemDto.class))
                .toList());
    }

    // "/api/chefs"
    @GetMapping
    ResponseEntity<List<ChefDto>> searchChefs(@RequestParam(required = false) String search) {
        if (search == null) {
            return ResponseEntity
                    .ok(chefService.getAllChefs()
                            .stream()
                            .map(chef -> modelMapper.map(chef, ChefDto.class))
                            .toList());
        } else {
            var searchResult = chefService.searchChefsByFirstNameOrLastName(search);
            if (searchResult.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            } else {
                return ResponseEntity.ok(searchResult
                        .stream()
                        .map(chef -> modelMapper.map(chef, ChefDto.class))
                        .toList());
            }
        }
    }

    // "/api/chefs/{id}"
    @DeleteMapping("{id}")
    ResponseEntity<Void> deleteChef(@PathVariable("id") int chefId) {
        if (chefService.removeChef(chefId)) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PatchMapping("{id}")
    ResponseEntity<Void> changeChef(@PathVariable("id") int chefId,
                                     @RequestBody @Valid UpdateChefDobDto updateChefDobDto) {
        if (chefService.changeChefDob(chefId, updateChefDobDto.getDateOfBirth())) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
