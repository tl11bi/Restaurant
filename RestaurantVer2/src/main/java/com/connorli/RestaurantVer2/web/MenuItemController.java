package com.connorli.RestaurantVer2.web;



import com.connorli.RestaurantVer2.domain.MenuItem;
import com.connorli.RestaurantVer2.repo.MenuItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.nio.file.Path;
import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@RestController
@RequestMapping(path = "/menuItems")
public class MenuItemController {
    private final MenuItemRepository menuItemRepository;

    @Autowired
    MenuItemController(MenuItemRepository menuItemRepository) {
        this.menuItemRepository = menuItemRepository;
    }



    @GetMapping
    List<MenuItem> all() {
        return menuItemRepository.findAll();
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    MenuItem newMenuItem(@RequestBody MenuItem newMenuItem) {
        return menuItemRepository.save(newMenuItem);
    }

    // Single item

    @GetMapping("/{menuItemID}")
    MenuItem one(@PathVariable Long menuItemID) {

        return menuItemRepository.findById(menuItemID)
                .orElseThrow(() -> new NoSuchElementException());
    }

    @PutMapping("/{menuItemID}")
    MenuItem replaceMenuItem(@RequestBody MenuItem newMenuItem, @PathVariable(value = "menuItemID") Long menuItemID) {

        return menuItemRepository.findById(menuItemID)
                .map(menuItem -> {

                    menuItem.setMenuItemName(newMenuItem.getMenuItemName());
                    menuItem.setPrice(newMenuItem.getPrice());
                    return menuItemRepository.save(menuItem);
                })
                .orElseGet(() -> {
                    newMenuItem.setMenuItemID(menuItemID);
                    return menuItemRepository.save(newMenuItem);
                });
    }

    @DeleteMapping("/{menuItemID}")
    void deleteMenuItem(@PathVariable Long menuItemID) {
        menuItemRepository.deleteById(menuItemID);
    }

}

