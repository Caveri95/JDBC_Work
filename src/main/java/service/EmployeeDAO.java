package service;

import model.Employee;

import java.util.List;

public interface EmployeeDAO {

    Employee addEmployee(Employee employee);

    Employee readEmployeeById(int id);

    List<Employee> readAllEmployee();

    Employee updateEmployee(Employee employee);

    void deleteEmployee(Employee employee);
}
