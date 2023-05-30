import service.CityDAO;
import service.CityDAOImpl;
import service.EmployeeDAO;
import service.EmployeeDAOImpl;
import model.City;
import model.Employee;

import java.util.List;

public class Application {

    public static void main(String[] args) {

        EmployeeDAO employeeDAO = new EmployeeDAOImpl();
        CityDAO cityDAO = new CityDAOImpl();


        //employeeDAO.addEmployee(new Employee("Malik", "Taran", "Male", 56, new City(7)));

        //employeeDAO.updateEmployee(new Employee(24,"Martaaaa", "Milena", "Female", 26, new City(7)));
        //employeeDAO.addEmployee(employee);
        //employeeDAO.delete(employee);
        //System.out.println(employeeDAO.readEmployeeById(12));

        /*List<Employee> list = employeeDAO.readAllEmployee();
        for (Employee emp : list) {
            System.out.println(emp);
        }*/

        cityDAO.addCity(new City("MSK"));

        List<City> list = cityDAO.readAllCity();
        list.forEach(System.out::println);

        City city = new City("ROSTOV");
        cityDAO.addCity(city);
        cityDAO.deleteCity(cityDAO.readCityById(7));
        cityDAO.updateCity(new City("NVG", 4));


    }
}
