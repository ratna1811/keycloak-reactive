package com.learning.keycloak_reactive.service;

import com.learning.keycloak_reactive.model.Employee;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.ArrayList;
import java.util.List;

@Service
public class EmployeeServiceImpl implements EmployeeService {

    private final List<Employee> employeeRepository = new ArrayList<>();

    @Override
    public Mono<Employee> createEmployee(Employee employee) {
        boolean exists = employeeRepository.stream()
                .anyMatch(emp -> emp.getId().equals(employee.getId()));
        if (exists) {
            return Mono.error(new RuntimeException("Employee with this ID already exists"));
        }
        employeeRepository.add(employee);
        return Mono.just(employee);
    }

    @Override
    public Flux<Employee> getAllEmployees() {
        return Flux.fromIterable(employeeRepository);
    }

    @Override
    public Mono<Employee> getEmployeeByUserId(String id) {
        return Mono.justOrEmpty(employeeRepository.stream()
                .filter(emp -> emp.getId().equals(id))
                .findFirst())
                .switchIfEmpty(Mono.error(new RuntimeException("Employee not found")));
    }
}
