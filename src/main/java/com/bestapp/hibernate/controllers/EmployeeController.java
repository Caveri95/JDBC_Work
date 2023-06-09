package com.bestapp.hibernate.controllers;

import com.bestapp.hibernate.model.City;
import com.bestapp.hibernate.model.Employee;
import com.bestapp.hibernate.services.api.EmployeeService;
import com.bestapp.hibernate.services.impl.EmployeeImpl;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/employee")
@Tag(name = "Сервис по работе с работниками", description = "CRUD-операции")
public class EmployeeController {

    private final EmployeeService employeeService;


    public EmployeeController(EmployeeImpl employeeService) {
        this.employeeService = employeeService;
    }

    @PostMapping("/add")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Работник добавлен", content = {
                    @Content(mediaType = "application/json", array = @ArraySchema(schema = @Schema(implementation = Employee.class)))}),
            @ApiResponse(responseCode = "400", description = "Параметры запроса отсутствуют или имеют некорректный формат"),
            @ApiResponse(responseCode = "500", description = "Произошла ошибка, не зависящая от вызывающей стороны")

    })
    @Operation(summary = "Добавить работника")
    public ResponseEntity<Employee> addEmployee(@RequestParam String firstName,
                                                @RequestParam String lastName,
                                                @RequestParam String gender,
                                                @RequestParam int age,
                                                @RequestParam int cityId,
                                                @RequestParam String cityName) {
        Employee employee = employeeService.addEmployee(new Employee(firstName, lastName, gender, age, new City(cityId, cityName)));
        return ResponseEntity.ok(employee);
    }

    @PutMapping("/update")
    @Operation(
            summary = "Изменит параметры работника"
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Параметры изменены", content = {
                    @Content(mediaType = "application/json", array = @ArraySchema(schema = @Schema(implementation = Employee.class)))}),
            @ApiResponse(responseCode = "400", description = "Параметры запроса отсутствуют или имеют некорректный формат"),
            @ApiResponse(responseCode = "404", description = "Такой работник не найден"),
            @ApiResponse(responseCode = "500", description = "Произошла ошибка, не зависящая от вызывающей стороны")

    })
    public ResponseEntity<Employee> updateEmployee(@RequestBody Employee employee) {
        Employee updatedEmployee = employeeService.updateEmployee(employee);
        if (updatedEmployee == null) {
            ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(updatedEmployee);
    }

    @GetMapping("/getAllEmployee")
    @Operation(
            summary = "Показать всех работников")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Список всех работников выведен", content = {
                    @Content(mediaType = "application/json", array = @ArraySchema(schema = @Schema(implementation = Employee.class)))}),
            @ApiResponse(responseCode = "400", description = "Параметры запроса отсутствуют или имеют некорректный формат"),
            @ApiResponse(responseCode = "404", description = "Такой работник не найден"),
            @ApiResponse(responseCode = "500", description = "Произошла ошибка, не зависящая от вызывающей стороны")

    })
    public ResponseEntity<List<Employee>> readAllEmployee() {
        List<Employee> employees = employeeService.readAllEmployee();
        if (employees == null) {
            ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(employees);
    }

    @GetMapping("/getEmployeeById")
    @Operation(
            summary = "Найти работника по его уникальному номеру")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Работник найден", content = {
                    @Content(mediaType = "application/json", array = @ArraySchema(schema = @Schema(implementation = Employee.class)))}),
            @ApiResponse(responseCode = "400", description = "Параметры запроса отсутствуют или имеют некорректный формат"),
            @ApiResponse(responseCode = "404", description = "Такой работник не найден"),
            @ApiResponse(responseCode = "500", description = "Произошла ошибка, не зависящая от вызывающей стороны")

    })
    public ResponseEntity<Employee> readEmployeeById(@RequestParam int id) {
        Employee employee = employeeService.readEmployeeById(id);
        if (employee == null) {
            return ResponseEntity.notFound().build();
        } else
            return ResponseEntity.ok(employee);

    }

    @DeleteMapping("/deleteEmployeeById")
    @Operation(summary = "Удалить работника из БД по его уникальному номеру")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Работник удален", content = {
                    @Content(mediaType = "application/json", array = @ArraySchema(schema = @Schema(implementation = Employee.class)))}),
            @ApiResponse(responseCode = "400", description = "Параметры запроса отсутствуют или имеют некорректный формат"),
            @ApiResponse(responseCode = "404", description = "Такой работник не найден"),
            @ApiResponse(responseCode = "500", description = "Произошла ошибка, не зависящая от вызывающей стороны")

    })
    public ResponseEntity<Void> deleteEmployee(@RequestParam int id) {
        if (employeeService.deleteEmployeeById(id)) {
            return ResponseEntity.ok().build();
        } else return ResponseEntity.notFound().build();
    }
}
