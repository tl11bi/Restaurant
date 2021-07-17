package com.connorli.RestaurantVer2.service;

import com.connorli.RestaurantVer2.domain.MenuItem;
import com.connorli.RestaurantVer2.repo.MenuItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
@Service
public class MenuItemService {
    private MenuItemRepository menuItemRepository;

    @Autowired
    public MenuItemService(MenuItemRepository menuItemRepository){
        this.menuItemRepository = menuItemRepository;
    }

    public MenuItem createMenuItem(String menuItemName, BigDecimal price){
        return menuItemRepository.findByMenuItemName(menuItemName).orElse(menuItemRepository.save(new MenuItem(menuItemName, price)));
    }

    public Iterable<MenuItem> lookup(){return menuItemRepository.findAll();}

    public long total() {
        return menuItemRepository.count();
    }

}
