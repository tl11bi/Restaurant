package com.connorli.RestaurantVer2.web;

import com.connorli.RestaurantVer2.domain.Employee;
import com.connorli.RestaurantVer2.domain.MenuItem;
import com.connorli.RestaurantVer2.domain.Order;
import com.connorli.RestaurantVer2.domain.RestTable;
import com.connorli.RestaurantVer2.repo.EmployeeRepository;
import com.connorli.RestaurantVer2.repo.MenuItemRepository;
import com.connorli.RestaurantVer2.repo.OrderRepository;
import com.connorli.RestaurantVer2.repo.RestTableRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.awt.*;
import java.nio.file.Path;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@RestController
@RequestMapping(path="/orders")
public class OrderController {
    private final OrderRepository orderRepository;
    private final EmployeeRepository employeeRepository;
    private final RestTableRepository restTableRepository;
    @Autowired
    OrderController(OrderRepository orderRepository, EmployeeRepository employeeRepository,
                    RestTableRepository restTableRepository){

        this.orderRepository = orderRepository;
        this.employeeRepository = employeeRepository;
        this.restTableRepository = restTableRepository;
     }

    @GetMapping
    List<Order> all() {
        return orderRepository.findAll();
    }


    @GetMapping("/{orderID}")
    Order one(@PathVariable Long orderID) {
        return orderRepository.findById(orderID)
                .orElseThrow(() -> new NoSuchElementException());
    }

    @PutMapping("/{orderID}")
    Order replaceOrder(@RequestBody Order newOrder, @PathVariable(value = "orderID") Long orderID) {

        return orderRepository.findById(orderID)
                .map(order -> {
                    order.setEmployee(newOrder.getEmployee());
                    order.setRestTable(newOrder.getRestTable());
                    return orderRepository.save(order);
                })
                .orElseGet(() -> {
                    newOrder.setOrderID(orderID);
                    return orderRepository.save(newOrder);
                });
    }


    @DeleteMapping("/{orderID}")
    void deleteOrder(@PathVariable Long orderID) {
        orderRepository.deleteById(orderID);
    }


    @PostMapping("/{employeeID}/{tableID}")
    @ResponseStatus(HttpStatus.CREATED)
    public Order createOrder(@PathVariable(value = "employeeID") Long employeeID, @PathVariable(value = "tableID") Long tableID){
        Employee employee = employeeRepository.findById(employeeID).orElseThrow(()->new NoSuchElementException());
        RestTable restTable = restTableRepository.findById(tableID).orElseThrow(()->new NoSuchElementException());
        return orderRepository.save(new Order(employee, restTable, new Date()));
    }





}
