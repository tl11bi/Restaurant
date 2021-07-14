package com.connorli.restaurant.JasonProduceData;

import com.connorli.restaurant.domain.*;
import com.fasterxml.jackson.databind.ObjectMapper;
import net.minidev.json.JSONObject;

import java.io.File;
import java.io.IOException;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

public class RestaurantDataGenerator {
    private List<Employee> employees = new ArrayList<>();
    private List<Order> orders = new ArrayList<>();
    private List<RestTable> restTables = new ArrayList<>();
    private List<MenuItem> menuItems = new ArrayList<>();
    private List<Reservation> reservations;
    private List<String> numbers= Arrays.asList("Zero", "One", "Two", "Three", "Four", "Five", "Six", "Seven", "Nine");
    final static int seed = 5;
    final static int serverSize = 3;
    final static int menuItemSize = 9;
    final static int tableSize = 9;
    public RestaurantDataGenerator(){

        Random random = new Random(seed);
        employees.addAll(Arrays.asList(new Employee("Admin", numbers.get(0), EmployeeType.Administrator),
                new Employee("Manager", numbers.get(0), EmployeeType.Manager),
                new Employee("Manager", numbers.get(1), EmployeeType.Manager)
                )
        );

        for (int i = 0; i < serverSize; i++) {
            employees.add(new Employee("Server", numbers.get(i), EmployeeType.Server));
        }

        for (int i = 0; i < menuItemSize; i++) {
            menuItems.add(new MenuItem("Item" + numbers.get(i), BigDecimal.valueOf((i/2)*10+10) ));
        }

        for (int i = 0; i < tableSize; i++) {
            restTables.add(new RestTable("Table"+numbers.get(i), i/3*5+5) );
        }

        List<Employee> servers = employees.stream().filter(a->a.getType()==EmployeeType.Server).collect(Collectors.toList());
        for(int i = 0; i < 10; i++){
            try{orders.add(new Order(servers.get(random.nextInt(servers.size())),
                    restTables.get(random.nextInt(restTables.size()))));}
            catch (ArrayIndexOutOfBoundsException E){
                System.out.println();
            }
        }

        for(int i = 0; i < 60; i++){
            orders.get(random.nextInt(orders.size())).addOrderItem(menuItems.get(random.nextInt(menuItems.size())));
        }

        reservations = Arrays.asList(new Reservation("resFirst1", "resLast1", Timestamp.valueOf("2020-08-19 14:20:05"),5, restTables.get(random.nextInt(restTables.size()))),
       new Reservation("resFirst2", "resLast2", Timestamp.valueOf("2020-09-20 15:20:05"),6, restTables.get(random.nextInt(restTables.size()))),
        new Reservation("resFirst3", "resLast3", Timestamp.valueOf("2020-10-21 16:20:05"),8, restTables.get(random.nextInt(restTables.size())))
        );

        ObjectMapper mapper = new ObjectMapper();
        File employeesFile = new File("employees.json");
        File ordersFile = new File("orders.json");
        File restTablesFile = new File("restTables.json");
        File menuItemsFile = new File("menuItems.json");
        File reservationsFile = new File("reservations.json");

        try {
            mapper.writeValue(employeesFile, employees);
            mapper.writeValue(ordersFile, orders);
            mapper.writeValue(restTablesFile, restTables);
            mapper.writeValue(menuItemsFile, menuItems);
            mapper.writeValue(reservationsFile, reservations);

        } catch (IOException e) {
            e.printStackTrace();
        }


    }
    public static void main(String[] args) {
        RestaurantDataGenerator restaurantDataGenerator  = new RestaurantDataGenerator();
    }
}
