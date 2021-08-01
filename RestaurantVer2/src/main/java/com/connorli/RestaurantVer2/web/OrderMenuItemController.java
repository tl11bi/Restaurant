package com.connorli.RestaurantVer2.web;

import com.connorli.RestaurantVer2.domain.Employee;
import com.connorli.RestaurantVer2.domain.MenuItem;
import com.connorli.RestaurantVer2.domain.Order;
import com.connorli.RestaurantVer2.domain.OrderMenuItem;
import com.connorli.RestaurantVer2.repo.MenuItemRepository;
import com.connorli.RestaurantVer2.repo.OrderMenuItemRepository;
import com.connorli.RestaurantVer2.repo.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@RestController
@RequestMapping(path = "/orderMenuItems")
public class OrderMenuItemController {
    private OrderMenuItemRepository orderMenuItemRepository;
    private OrderRepository orderRepository;
    private MenuItemRepository menuItemRepository;

    @Autowired
    public OrderMenuItemController(OrderMenuItemRepository orderMenuItemRepository, OrderRepository orderRepository, MenuItemRepository menuItemRepository) {
        this.orderMenuItemRepository = orderMenuItemRepository;
        this.orderRepository = orderRepository;
        this.menuItemRepository = menuItemRepository;
    }

    @GetMapping
    List<OrderMenuItem> all() {
        return orderMenuItemRepository.findAll();
    }

    @PostMapping("/{orderID}/{menuItemID}")
    @ResponseStatus(HttpStatus.CREATED)
    OrderMenuItem newOrderMenuItem(@PathVariable(value = "orderID") Long orderID, @PathVariable(value = "menuItemID") Long menuItemID) {
        Order order = orderRepository.findById(orderID).orElseThrow(()-> new NoSuchElementException());
        MenuItem menuItem = menuItemRepository.findById(menuItemID).orElseThrow(()->new NoSuchElementException());
        return orderMenuItemRepository.save(new OrderMenuItem(order, menuItem));
    }

    // Single item
    @GetMapping("/{orderMenuItemID}")
    OrderMenuItem one(@PathVariable Long orderMenuItemID) {

        return orderMenuItemRepository.findById(orderMenuItemID)
                .orElseThrow(() -> new NoSuchElementException());
    }


    @DeleteMapping("/{orderMenuItemID}")
    @ResponseStatus(HttpStatus.CREATED)
    void deleteOrderMenuItem(@PathVariable(value = "orderMenuItemID") Long orderMenuItemID) {
        orderMenuItemRepository.deleteById(orderMenuItemID);
    }




}
