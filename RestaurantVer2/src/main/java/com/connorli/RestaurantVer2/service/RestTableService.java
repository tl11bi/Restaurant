package com.connorli.RestaurantVer2.service;

import com.connorli.RestaurantVer2.domain.RestTable;
import com.connorli.RestaurantVer2.repo.ReservationRepository;
import com.connorli.RestaurantVer2.repo.RestTableRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class RestTableService {
    private RestTableRepository restTableRepository;

    public RestTableService(RestTableRepository restTableRepository) {
        this.restTableRepository = restTableRepository;
    }

    public RestTable createRestTable(String tableName, int capacity){
        return restTableRepository.getRestTableByTableName(tableName).orElse(new RestTable(tableName, capacity));
    }

    public Iterable<RestTable> lookup(){return restTableRepository.findAll();}

    public List<RestTable> getAll(){return restTableRepository.findAll();}

    public long total(){return restTableRepository.count();}

}
