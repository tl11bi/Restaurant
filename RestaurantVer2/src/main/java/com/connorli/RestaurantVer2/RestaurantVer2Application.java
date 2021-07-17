package com.connorli.RestaurantVer2;

import com.connorli.RestaurantVer2.domain.Employee;
import com.connorli.RestaurantVer2.domain.EmployeeType;
import com.connorli.RestaurantVer2.domain.RestTable;
import com.connorli.RestaurantVer2.service.*;
import javafx.scene.control.RadioMenuItem;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

@SpringBootApplication
public class RestaurantVer2Application implements CommandLineRunner {
    final static int seed = 5;
    final static int serverSize = 3;
    final static int menuItemSize = 9;
    final static int tableSize = 9;
    private static Random random = new Random(5);
    private List<String> numbers = Arrays.asList("Zero", "One", "Two", "Three", "Four", "Five", "Six", "Seven", "Nine");
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
    }

    public static void main(String[] args) {
        SpringApplication.run(RestaurantVer2Application.class, args);
    }

    private void createAll() {
        createEmployees();
        createMenuItems();

        createTable();

        List<Employee> servers = employeeService.getEmployeeByType(EmployeeType.Server);
        List<RestTable> restTables = restTableService.
        for (int i = 0; i < 10; i++) {
            try {
                orderService.createOrder(servers.get(random.nextInt(servers.size())),
                        restTables.get(random.nextInt(restTables.size())));
            } catch (ArrayIndexOutOfBoundsException E) {
                System.out.println();
            }
        }

        for (int i = 0; i < 60; i++) {
            orders.get(random.nextInt(orders.size())).addOrderItem(menuItems.get(random.nextInt(menuItems.size())));
        }

        reservations = Arrays.asList(new Reservation("resFirst1", "resLast1", Timestamp.valueOf("2020-08-19 14:20:05"), 5, restTables.get(random.nextInt(restTables.size()))),
                new Reservation("resFirst2", "resLast2", Timestamp.valueOf("2020-09-20 15:20:05"), 6, restTables.get(random.nextInt(restTables.size()))),
                new Reservation("resFirst3", "resLast3", Timestamp.valueOf("2020-10-21 16:20:05"), 8, restTables.get(random.nextInt(restTables.size())))
        );
    }

    private void createTable() {
        for (int i = 0; i < tableSize; i++) {
            restTableService.createRestTable("Table" + numbers.get(i), i / 3 * 5 + 5);
        }
    }

    private void createMenuItems() {
        for (int i = 0; i < menuItemSize; i++) {
            menuItemService.createMenuItem("Item" + numbers.get(i), BigDecimal.valueOf((i / 2) * 10 + 10));
        }
    }

    private void createEmployees() {
        employeeService.createEmployee("Admin", numbers.get(0), EmployeeType.Administrator);
        employeeService.createEmployee("Manager", numbers.get(0), EmployeeType.Manager);
        employeeService.createEmployee("Manager", numbers.get(1), EmployeeType.Manager);
        for (int i = 0; i < serverSize; i++) {
            employeeService.createEmployee("Server", numbers.get(i), EmployeeType.Server);
        }
    }


}
