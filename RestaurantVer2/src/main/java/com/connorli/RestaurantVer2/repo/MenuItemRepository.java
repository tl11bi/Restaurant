package com.connorli.RestaurantVer2.repo;

import com.connorli.RestaurantVer2.domain.Employee;
import com.connorli.RestaurantVer2.domain.MenuItem;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;

public interface MenuItemRepository extends CrudRepository<MenuItem, Integer> {
    Optional<MenuItem> findByMenuItemID(Integer menuItemID);
    Optional<MenuItem> findByMenuItemName(String menuItemName);
    List<MenuItem> findAll();
}
