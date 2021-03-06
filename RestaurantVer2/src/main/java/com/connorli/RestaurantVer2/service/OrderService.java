package com.connorli.RestaurantVer2.service;

import com.connorli.RestaurantVer2.domain.Employee;
import com.connorli.RestaurantVer2.domain.MenuItem;
import com.connorli.RestaurantVer2.domain.Order;
import com.connorli.RestaurantVer2.domain.RestTable;
import com.connorli.RestaurantVer2.repo.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

@Service
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

    public void saveOrder(Order order){
        orderRepository.save(order);
    }
    public Iterable<Order> lookup(){return orderRepository.findAll();}
    public List<Order> getOrders(){
        return orderRepository.findAll();
    }

    public long total() {
        return orderRepository.count();
    }
}
