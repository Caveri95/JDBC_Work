package DAO;

import model.Employee;

import java.sql.SQLException;
import java.util.List;

public interface EmployeeDAO {

    void addEmployee(Employee employee) throws SQLException;

    Employee readEmployeeById(int id) throws SQLException;

    List<Employee> readAllEmployee() throws SQLException;

    void updateEmployee(int id, String firstName, String lastName, String gender, int age, int cityId) throws SQLException;

    void deleteById(int id) throws SQLException;
}
