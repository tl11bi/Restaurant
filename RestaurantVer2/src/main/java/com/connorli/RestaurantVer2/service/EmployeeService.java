package com.connorli.RestaurantVer2.service;

import com.connorli.RestaurantVer2.domain.Employee;
import com.connorli.RestaurantVer2.domain.EmployeeType;
import com.connorli.RestaurantVer2.repo.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;

public class EmployeeService {
    @Autowired
    public EmployeeService(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }

    private EmployeeRepository employeeRepository;

    public Employee createEmployee(String firstName, String lastName, EmployeeType employeeType){
        return employeeRepository.findByFirstNameAndLastName(firstName, lastName)
                .orElse(employeeRepository.save(new Employee(firstName, lastName, employeeType)));
    }

    public Iterable<Employee> lookup(){return employeeRepository.findAll();}
    public long total(){
        return employeeRepository.count();
    }
}
