package com.connorli.RestaurantVer2.repo;

import com.connorli.RestaurantVer2.domain.Employee;
import com.connorli.RestaurantVer2.domain.RestTable;
import org.springframework.data.repository.CrudRepository;

public interface RestTableRepository extends CrudRepository<RestTable, Integer> {
}
