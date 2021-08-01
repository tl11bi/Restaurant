package com.connorli.RestaurantVer2;

import com.connorli.RestaurantVer2.domain.*;
import com.connorli.RestaurantVer2.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.math.BigDecimal;
import java.sql.Timestamp;

@SpringBootApplication
public class RestaurantVer2Application implements CommandLineRunner {

    @Autowired
    private EmployeeService employeeService;
    @Autowired
    private MenuItemService menuItemService;
    @Autowired
    private OrderService orderService;
    @Autowired
    private ReservationService reservationService;
    @Autowired
    private RestTableService restTableService;
    @Autowired
    private OrderMenuItemService orderMenuItemService;
    @Override
    public void run(String... args) throws Exception {
        //createNewItems();

    }

    private void createNewItems(){

        employeeService.createEmployee("Connor", "Li", EmployeeType.Server);
        menuItemService.createMenuItem("King Shoyou", BigDecimal.valueOf(20));
        restTableService.createRestTable("A1", 6);
        reservationService.createReservation("Jason", "Chen", Timestamp.valueOf("2020-08-19 14:20:05"), 5, restTableService.getTables().get(0));
        orderService.createOrder(employeeService.getEmployees().get(0), restTableService.getTables().get(0), Timestamp.valueOf("2020-08-19 14:20:05"));
        orderMenuItemService.createOrderMenuItem(orderService.getOrders().get(0), menuItemService.getMenuItems().get(0));
    }

    public static void main(String[] args) {
        SpringApplication.run(RestaurantVer2Application.class, args);
    }




}
