package com.learning.keycloak_reactive.controller;

//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.learning.keycloak_reactive.model.Employee;
import com.learning.keycloak_reactive.service.EmployeeService;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/employees")
public class EmployeeController {

    // private final Logger LOGGER = LoggerFactory.getLogger(Employee.class);

    @Autowired
    private EmployeeService employeeService;

    @PostMapping("/admin")
    public Mono<Employee> createEmployee(@RequestBody Employee employee) {
        return employeeService.createEmployee(employee);
    }

    @GetMapping("/admin")
    public Flux<Employee> getAllEmployees() {
        return employeeService.getAllEmployees();
    }

    @GetMapping("/user/{id}")
    public Mono<Employee> getEmployeeByUserId(@PathVariable String id) {
        return employeeService.getEmployeeByUserId(id);
    }

}
