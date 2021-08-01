package com.connorli.RestaurantVer2.repo;

import com.connorli.RestaurantVer2.domain.Employee;
import com.connorli.RestaurantVer2.domain.MenuItem;
import com.connorli.RestaurantVer2.domain.Order;
import com.connorli.RestaurantVer2.domain.RestTable;
import org.springframework.data.repository.CrudRepository;

import java.util.Date;
import java.util.List;
import java.util.Optional;

public interface OrderRepository extends CrudRepository<Order, Long> {
    Optional<Order> findOrderByEmployeeAndRestTableAndTime(Employee employee, RestTable restTable, Date time);
    List<Order> findAll();

}
