package com.connorli.RestaurantVer2.repo;


import com.connorli.RestaurantVer2.domain.MenuItem;
import com.connorli.RestaurantVer2.domain.OrderMenuItem;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import java.util.List;

@RepositoryRestResource(collectionResourceRel = "OrderMenuItem", path = "OrderMenuItem")
public interface OrderMenuItemRepository extends CrudRepository<OrderMenuItem, Long> {
    List<OrderMenuItem> findAll();

}
