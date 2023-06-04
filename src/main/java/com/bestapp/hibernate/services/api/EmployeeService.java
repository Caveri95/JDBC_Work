package com.bestapp.hibernate.services.api;

import com.bestapp.hibernate.model.Employee;

import java.util.List;

public interface EmployeeService {

    Employee addEmployee(Employee employee);

    Employee readEmployeeById(int id);

    List<Employee> readAllEmployee();

    Employee updateEmployee(Employee employee);

    void deleteEmployee(Employee employee);
}
