package com.connorli.restaurant.JasonProduceData;

import com.connorli.restaurant.domain.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class RestaurantDataGenerator {
    private List<Employee> employees = new ArrayList<>();
    private List<Order> orders = new ArrayList<>();
    private List<RestTable> restTables = new ArrayList<>();
    private List<MenuItem> menuItems = new ArrayList<>();
    private List<Reservation> reservations = new ArrayList<>();
    private List<String> numbers= Arrays.asList("Zero", "One", "Two", "Three", "Four", "Five", "Six", "Seven", "Nine");
    public RestaurantDataGenerator(){

        employees.addAll(Arrays.asList(new Employee("Admin", numbers.get(0), EmployeeType.Administrator),
                new Employee("Admin", numbers.get(1), EmployeeType.Administrator),
                new Employee("Manager", numbers.get(0), EmployeeType.Manager),
                new Employee("Manager", numbers.get(1), EmployeeType.Manager),
                new Employee("Manager", numbers.get(2), EmployeeType.Manager)
                )
        );

        for (int i = 0; i < 6; i++) {
            employees.add(new Employee("Server", numbers.get(i), EmployeeType.Server));
        }

        for (int i = 0; i < 9; i++) {
            menuItems.add(new MenuItem("Item" + numbers.get(i), (i+1) ))

        }
    }
    public static void main(String[] args) {
        RestaurantDataGenerator restaurantDataGenerator  = new RestaurantDataGenerator();
    }
}
