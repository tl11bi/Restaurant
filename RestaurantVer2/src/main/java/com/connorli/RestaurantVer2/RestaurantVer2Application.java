package com.connorli.RestaurantVer2;

import com.connorli.RestaurantVer2.GenData.RestaurantDataGenerator;
import com.connorli.RestaurantVer2.domain.*;
import com.connorli.RestaurantVer2.repo.*;
import com.connorli.RestaurantVer2.service.*;
import javafx.scene.control.RadioMenuItem;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import javax.sql.DataSource;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

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


    @Override
    public void run(String... args) throws Exception {
        //createNewItems();

    }

    private void createNewItems(){
        employeeService.createEmployee("Connor", "Li", EmployeeType.Server);
    }

    public static void main(String[] args) {
        SpringApplication.run(RestaurantVer2Application.class, args);
    }




}
