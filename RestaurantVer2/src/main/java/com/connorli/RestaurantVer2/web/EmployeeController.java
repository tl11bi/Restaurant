package com.connorli.RestaurantVer2.web;

import com.connorli.RestaurantVer2.domain.Employee;
import com.connorli.RestaurantVer2.repo.EmployeeRepository;
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
@RequestMapping(path = "/employees")
public class EmployeeController {
    private final EmployeeRepository employeeRepository;

    @Autowired
    EmployeeController(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }



    @GetMapping
    List<Employee> all() {
        return employeeRepository.findAll();
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    Employee newEmployee(@RequestBody Employee newEmployee) {
        return employeeRepository.save(newEmployee);
    }

    // Single item

    @GetMapping("/{employeeID}")
    Employee one(@PathVariable Long employeeID) {

        return employeeRepository.findById(employeeID)
                .orElseThrow(() -> new NoSuchElementException());
    }

    @PutMapping("/{employeeID}")
    Employee replaceEmployee(@RequestBody Employee newEmployee, @PathVariable(value = "employeeID") Long employeeID) {

        return employeeRepository.findById(employeeID)
                .map(employee -> {
                    employee.setFirstName(newEmployee.getFirstName());
                    employee.setLastName(newEmployee.getLastName());
                    employee.setType(newEmployee.getType());
                    return employeeRepository.save(employee);
                })
                .orElseGet(() -> {
                    newEmployee.setEmployeeID(employeeID);
                    return employeeRepository.save(newEmployee);
                });
    }

    @DeleteMapping("/{employeeID}")
    void deleteEmployee(@PathVariable Long employeeID) {
        employeeRepository.deleteById(employeeID);
    }

}
