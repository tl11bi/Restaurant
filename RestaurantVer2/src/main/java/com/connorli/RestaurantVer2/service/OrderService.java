package com.connorli.RestaurantVer2.service;

import com.connorli.RestaurantVer2.domain.MenuItem;
import com.connorli.RestaurantVer2.repo.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;

import java.math.BigDecimal;

public class OrderService {
    private OrderRepository orderRepository;

    @Autowired
    public OrderService(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }


    public MenuItem createMenuItem(String menuItemName, BigDecimal price){
        return orderRepository.findByMenuItemName(menuItemName).orElse(orderRepository.save(new MenuItem(menuItemName, price)));
    }

    public Iterable<MenuItem> lookup(){return menuItemRepository.findAll();}

    public long total() {
        return menuItemRepository.count();
    }
}
