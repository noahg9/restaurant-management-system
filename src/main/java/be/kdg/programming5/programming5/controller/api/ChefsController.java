package be.kdg.programming5.programming5.controller.api;

import be.kdg.programming5.programming5.dto.ChefDto;
import be.kdg.programming5.programming5.dto.MenuItemDto;
import be.kdg.programming5.programming5.dto.NewChefDto;
import be.kdg.programming5.programming5.domain.MenuAssignment;
import be.kdg.programming5.programming5.domain.Chef;
import be.kdg.programming5.programming5.domain.ChefRole;
import be.kdg.programming5.programming5.repository.ChefRepository;
import be.kdg.programming5.programming5.service.ChefService;
import jakarta.validation.Valid;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

/**
 * The type Chefs controller.
 */
@RestController
@RequestMapping("/api/chefs")
public class ChefsController {
    private final ChefService chefService;
    private final ChefRepository chefRepository;
    private final ModelMapper modelMapper;
    private final BCryptPasswordEncoder passwordEncoder;

    /**
     * Instantiates a new Chefs controller.
     *
     * @param chefService     the chef service
     * @param chefRepository  the chef repository
     * @param modelMapper     the model mapper
     * @param passwordEncoder the password encoder
     */
    public ChefsController(ChefService chefService, ChefRepository chefRepository, ModelMapper modelMapper, BCryptPasswordEncoder passwordEncoder) {
        this.chefService = chefService;
        this.chefRepository = chefRepository;
        this.modelMapper = modelMapper;
        this.passwordEncoder = passwordEncoder;
    }

    /**
     * Gets chef.
     *
     * @param chefId the chef id
     * @return the chef
     */
    @GetMapping("{id}")
    ResponseEntity<ChefDto> getChef(@PathVariable("id") long chefId) {
        Chef chef = chefService.getChef(chefId);
        if (chef == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return ResponseEntity.ok(modelMapper.map(chef, ChefDto.class));
    }

    /**
     * Gets all chefs.
     *
     * @return the all chefs
     */
    @GetMapping
    ResponseEntity<List<ChefDto>> getAllChefs() {
        List<Chef> allChefs = chefService.getAllChefs();
        if (allChefs.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } else {
            List<ChefDto> chefDtos = allChefs.stream().map(chef -> modelMapper.map(chef, ChefDto.class)).collect(Collectors.toList());
            return ResponseEntity.ok(chefDtos);
        }
    }

    /**
     * Gets menu items of chef.
     *
     * @param chefId the chef id
     * @return the menu items of chef
     */
    @GetMapping("{id}/menu-items")
    ResponseEntity<List<MenuItemDto>> getMenuItemsOfChef(@PathVariable("id") long chefId) {
        Chef chef = chefService.getChefWithMenuItems(chefId);
        if (chef == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        if (chef.getMenuItems().isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return ResponseEntity.ok(chef.getMenuItems().stream().map(MenuAssignment::getMenuItem).map(dev -> modelMapper.map(dev, MenuItemDto.class)).toList());
    }

    /**
     * Search chefs response entity.
     *
     * @param search the search
     * @return the response entity
     */
    @GetMapping("search")
    ResponseEntity<List<ChefDto>> searchChefs(@RequestParam(required = false) java.lang.String search) {
        if (search == null || search.trim().isEmpty()) {
            return ResponseEntity.ok(chefService.getAllChefs().stream().map(chef -> modelMapper.map(chef, ChefDto.class)).toList());
        } else {
            List<Chef> searchResult = chefService.searchChefsByFirstNameOrLastName(search);
            if (searchResult.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            } else {
                return ResponseEntity.ok(searchResult.stream().map(chef -> modelMapper.map(chef, ChefDto.class)).toList());
            }
        }
    }

    /**
     * Create chef response entity.
     *
     * @param chefDto the chef dto
     * @return the response entity
     */
    @PostMapping
    ResponseEntity<ChefDto> createChef(@RequestBody @Valid NewChefDto chefDto) {
        if (chefRepository.findByUsername(chefDto.getUsername()).isPresent()) {
            return new ResponseEntity<>(HttpStatus.CONFLICT);
        }
        Chef createdChef = chefService.saveChef(chefDto.getFirstName(), chefDto.getLastName(), chefDto.getDateOfBirth(), chefDto.getUsername(), passwordEncoder.encode(chefDto.getPassword()), ChefRole.fromName(chefDto.getRoleName()));
        return new ResponseEntity<>(modelMapper.map(createdChef, ChefDto.class), HttpStatus.CREATED);
    }

    /**
     * Delete chef response entity.
     *
     * @param chefId the chef id
     * @return the response entity
     */
    @DeleteMapping("{id}")
    ResponseEntity<Void> deleteChef(@PathVariable("id") long chefId) {
        if (chefService.deleteChef(chefId)) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
}
