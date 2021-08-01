package com.connorli.RestaurantVer2.service;

import com.connorli.RestaurantVer2.domain.*;
import com.connorli.RestaurantVer2.domain.MenuItem;
import com.connorli.RestaurantVer2.repo.OrderMenuItemRepository;
import org.springframework.stereotype.Service;

import java.awt.*;
import java.util.List;

@Service
public class OrderMenuItemService {
    private OrderMenuItemRepository orderMenuItemRepository;

    public OrderMenuItemService(OrderMenuItemRepository orderMenuItemRepository) {
        this.orderMenuItemRepository = orderMenuItemRepository;
    }

    public OrderMenuItem createOrderMenuItem(Order order, MenuItem menuItem){
        return orderMenuItemRepository.save(new OrderMenuItem(order, menuItem));
    }

    public List<OrderMenuItem> getOrderMenuItems(){
        return orderMenuItemRepository.findAll();
    }
}
