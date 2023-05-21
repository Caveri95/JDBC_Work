package DAO;

import model.Employee;

import java.sql.SQLException;
import java.util.List;

public interface EmployeeDAO {

    void addEmployee(Employee employee);

    Employee readEmployeeById(int id);

    List<Employee> readAllEmployee();

    void updateEmployee(Employee employee);

    void delete(Employee employee);
}
