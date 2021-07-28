package com.connorli.RestaurantVer2.repo;

import com.connorli.RestaurantVer2.domain.Employee;
import com.connorli.RestaurantVer2.domain.RestTable;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;

public interface RestTableRepository extends CrudRepository<RestTable, Long> {
    Optional<RestTable> getRestTableByTableName(String tableName);
    List<RestTable> findAll();

}
