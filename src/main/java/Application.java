import DAO.EmployeeDAO;
import DAO.EmployeeDAOImpl;
import model.City;
import model.Employee;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class Application {

    public static void main(String[] args) throws SQLException {

        final String user = "postgres";
        final String password = "prikol";
        final String url = "jdbc:postgresql://localhost:5432/skypro";

        try (Connection connection = DriverManager.getConnection(url, user, password);

             PreparedStatement statement = connection.prepareStatement("SELECT employee.first_name, employee.last_name, " +
                     "employee.gender, city.city_name " +
                     "FROM employee LEFT JOIN city ON city.city_id = employee.city_id WHERE id = (?)")
        ) {

            statement.setInt(1, 9);
            final ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                System.out.printf("Name - %s, surname - %s, gender - %s, city - %s \n",
                        resultSet.getString("first_name"),
                        resultSet.getString("last_name"),
                        resultSet.getString("gender"),
                        resultSet.getString("city_name"));
            }
        }

        try (Connection connection = DriverManager.getConnection(url, user, password)) {

            EmployeeDAO employeeDAO = new EmployeeDAOImpl(connection);

            employeeDAO.addEmployee(new Employee("Anna", "Marko", "Female", 44, new City(4)));

            System.out.println(employeeDAO.readEmployeeById(20));

            List<Employee> employees = new ArrayList<>(employeeDAO.readAllEmployee());
            for (Employee employee : employees) {
                System.out.printf("id - %d, first_name - %s, last_name - %s, gender - %s, age - %d, city - %s \n",
                        employee.getId(), employee.getFirst_name(),
                        employee.getLast_name(),
                        employee.getGender(),
                        employee.getAge(),
                        employee.getCity().getCity_name());
            }

            employeeDAO.updateEmployee(20, "Mike", "Hit", "Male", 29, 1);

            employeeDAO.deleteById(19);
        }
    }
}
