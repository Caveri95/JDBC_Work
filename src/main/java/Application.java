import DAO.EmployeeDAO;
import DAO.EmployeeDAOImpl;
import model.Employee;

import java.util.List;

public class Application {

    public static void main(String[] args) {

        EmployeeDAO employeeDAO = new EmployeeDAOImpl();

        Employee employee = new Employee(15,"Marta", "Milena", "Female", 26, 1);
        employeeDAO.updateEmployee(employee);
        employeeDAO.addEmployee(employee);
        employeeDAO.delete(employee);
        employeeDAO.readEmployeeById(1);

        List<Employee> list = employeeDAO.readAllEmployee();
        for (Employee emp : list) {
            System.out.println(emp);
        }
    }
}
