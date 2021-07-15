package com.connorli.RestaurantVer2.service;

import com.connorli.RestaurantVer2.domain.Employee;
import com.connorli.RestaurantVer2.domain.MenuItem;
import com.connorli.RestaurantVer2.domain.Order;
import com.connorli.RestaurantVer2.domain.RestTable;
import com.connorli.RestaurantVer2.repo.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;

import java.math.BigDecimal;
import java.util.Date;

public class OrderService {
    private OrderRepository orderRepository;

    @Autowired
    public OrderService(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }


    public Order createOrder(Employee employee, RestTable restTable, Date time){
        return orderRepository.findOrderByEmployeeAndRestTableAndTime(employee, restTable, time)
                .orElse(orderRepository.save(new Order(employee, restTable, time)));
    }

    public Iterable<Order> lookup(){return orderRepository.findAll();}

    public long total() {
        return orderRepository.count();
    }
}
