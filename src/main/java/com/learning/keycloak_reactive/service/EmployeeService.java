package com.learning.keycloak_reactive.service;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.springframework.stereotype.Service;

import com.learning.keycloak_reactive.model.Employee;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class EmployeeService {
    private final List<Employee> employees = new ArrayList<>();

    public Mono<Employee> createEmployee(Employee employee) {
        employee.setId(UUID.randomUUID().toString());
        employees.add(employee);
        return Mono.just(employee);
    }

    public Flux<Employee> getAllEmployees() {
        return Flux.fromIterable(employees);
    }

    public Mono<Employee> getEmployeeByUserId(String id) {
        return Flux.fromIterable(employees)
                .filter(employee -> employee.getId().equals(id))
                .next();
    }
}
