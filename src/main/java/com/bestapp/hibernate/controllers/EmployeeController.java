package com.bestapp.hibernate.controllers;

import com.bestapp.hibernate.model.City;
import com.bestapp.hibernate.model.Employee;
import com.bestapp.hibernate.services.api.EmployeeService;
import com.bestapp.hibernate.services.impl.EmployeeImpl;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/employee")
@Tag(name = "Сервис по работе с работниками", description = "CRUD-операции")
public class EmployeeController {

    private final EmployeeService employeeService;


    public EmployeeController(EmployeeImpl employeeService) {
        this.employeeService = employeeService;
    }

    @PostMapping("/add")
    @Operation(summary = "Добавить работника")
    public ResponseEntity<Employee> addEmployee(@RequestParam String firstName,
                                                @RequestParam String lastName,
                                                @RequestParam String gender,
                                                @RequestParam int age,
                                                @RequestParam String cityName) {
               Employee employee = employeeService.addEmployee(new Employee(firstName, lastName, gender, age, new City(cityName)));
        return ResponseEntity.ok(employee);
    }

}
