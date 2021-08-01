package com.connorli.RestaurantVer2.web;

import com.connorli.RestaurantVer2.domain.RestTable;
import com.connorli.RestaurantVer2.repo.RestTableRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.nio.file.Path;
import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@RestController
@RequestMapping(path = "/resttables")
public class RestTableController {
    private final RestTableRepository restTableRepository;

    @Autowired
    RestTableController(RestTableRepository restTableRepository) {
        this.restTableRepository = restTableRepository;
    }



    @GetMapping
    List<RestTable> all() {
        return restTableRepository.findAll();
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    RestTable newRestTable(@RequestBody RestTable newRestTable) {
        return restTableRepository.save(newRestTable);
    }

    // Single item

    @GetMapping("/{restTableID}")
    RestTable one(@PathVariable Long restTableID) {

        return restTableRepository.findById(restTableID)
                .orElseThrow(() -> new NoSuchElementException());
    }

    @PutMapping("/{restTableID}")
    RestTable replaceRestTable(@RequestBody RestTable newRestTable, @PathVariable(value = "restTableID") Long restTableID) {

        return restTableRepository.findById(restTableID)
                .map(restTable -> {
                    restTable.setTableName(newRestTable.getTableName());
                    restTable.setCapacity(newRestTable.getCapacity());
                    restTable.setOccupied(newRestTable.isOccupied());
                    return restTableRepository.save(restTable);
                })
                .orElseGet(() -> {
                    newRestTable.setTableID(restTableID);
                    return restTableRepository.save(newRestTable);
                });
    }

    @DeleteMapping("/{restTableID}")
    void deleteRestTable(@PathVariable Long restTableID) {
        restTableRepository.deleteById(restTableID);
    }

}
