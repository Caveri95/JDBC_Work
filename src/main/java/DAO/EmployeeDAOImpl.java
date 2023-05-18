package DAO;

import model.City;
import model.Employee;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class EmployeeDAOImpl implements EmployeeDAO {

    private final Connection connection;

    public EmployeeDAOImpl(Connection connection) {
        this.connection = connection;
    }

    @Override
    public void addEmployee(Employee employee) throws SQLException {
        try (PreparedStatement statement = connection.prepareStatement("INSERT INTO employee (first_name, " +
                "last_name, gender, age, city_id) VALUES ((?), (?), (?), (?), (?))")) {

            statement.setString(1, employee.getFirstName());
            statement.setString(2, employee.getLastName());
            statement.setString(3, employee.getGender());
            statement.setInt(4, employee.getAge());
            statement.setInt(5, employee.getCity().getCityId());

            statement.executeUpdate();

        }
    }

    @Override
    public Employee readEmployeeById(int id) throws SQLException {
        Employee employee = new Employee();
        try (PreparedStatement statement = connection.prepareStatement("SELECT employee.id, employee.first_name, employee.last_name, " +
                "employee.gender, employee.age, city.city_name, city.city_id " +
                "FROM employee LEFT JOIN city ON city.city_id = employee.city_id WHERE id = (?)")) {

            statement.setInt(1, id);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {

                employee.setId(resultSet.getInt("id"));
                employee.setFirstName(resultSet.getString("first_name"));
                employee.setLastName(resultSet.getString("last_name"));
                employee.setGender(resultSet.getString("gender"));
                employee.setAge(resultSet.getInt("age"));
                employee.setCity(new City(resultSet.getString("city_name"), resultSet.getInt("city_id")));
            }

        }
        return employee;
    }

    @Override
    public List<Employee> readAllEmployee() throws SQLException {
        List<Employee> employees = new ArrayList<>();
        try (PreparedStatement statement = connection.prepareStatement("SELECT employee.id, employee.first_name, employee.last_name, employee.gender, employee.age, city.city_id, city.city_name FROM employee INNER JOIN city on city.city_id = employee.city_id ORDER BY employee.id")) {
            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                String firstName = resultSet.getString("first_name");
                String lastName = resultSet.getString("last_name");
                String gender = resultSet.getString("gender");
                int age = Integer.parseInt(resultSet.getString("age"));
                String cityName = resultSet.getString("city_name");
                int cityId = resultSet.getInt("city_id");
                employees.add(new Employee(id, firstName, lastName, gender, age, new City(cityName, cityId)));
            }
        }
        return employees;
    }

    @Override
    public void updateEmployee(int id, String firstName, String lastName, String gender, int age, int cityId) throws SQLException {
        try (PreparedStatement statement = connection.prepareStatement("UPDATE employee SET first_name = (?), last_name = (?), gender = (?), age = (?), city_id = (?) WHERE  id = (?)")) {

            statement.setString(1, firstName);
            statement.setString(2, lastName);
            statement.setString(3, gender);
            statement.setInt(4, age);
            statement.setInt(5, cityId);
            statement.setInt(6, id);

            statement.executeUpdate();
        }
    }

    @Override
    public void deleteById(int id) throws SQLException {
        try (PreparedStatement statement = connection.prepareStatement("DELETE FROM employee WHERE id = (?)")) {

            statement.setInt(1, id);
            statement.executeUpdate();
        }
    }
}
