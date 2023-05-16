import java.sql.*;

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
            statement.setInt(1,9);
            final ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                System.out.printf("Name - %s, surname - %s, gender - %s, city - %s",
                        resultSet.getString("first_name"),
                        resultSet.getString("last_name"),
                        resultSet.getString("gender"),
                        resultSet.getString("city_name"));
            }

        }

    }
}
