package com.learning.keycloak_reactive.service;

import com.learning.keycloak_reactive.model.Employee;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface EmployeeService {
    Mono<Employee> createEmployee(Employee employee);

    Flux<Employee> getAllEmployees();

    Mono<Employee> getEmployeeByUserId(String id);
}
