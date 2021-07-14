package com.connorli.RestaurantVer2.repo;

import com.connorli.RestaurantVer2.domain.Employee;
import com.connorli.RestaurantVer2.domain.Order;
import org.springframework.data.repository.CrudRepository;

public interface OrderRepository extends CrudRepository<Order, Integer> {
}
