package com.bestapp.hibernate.controllers;

import com.bestapp.hibernate.model.City;
import com.bestapp.hibernate.model.Employee;
import com.bestapp.hibernate.services.api.CityService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/city")
@Tag(name = "Сервис по работе с городами", description = "CRUD-операции")
public class CityController {

    private final CityService cityService;


    public CityController(CityService cityService) {
        this.cityService = cityService;
    }

    @PostMapping("/add")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Город добавлен", content = {
                    @Content(mediaType = "application/json", array = @ArraySchema(schema = @Schema(implementation = City.class)))}),
            @ApiResponse(responseCode = "400", description = "Параметры запроса отсутствуют или имеют некорректный формат"),
            @ApiResponse(responseCode = "500", description = "Произошла ошибка, не зависящая от вызывающей стороны")

    })
    @Operation(summary = "Добавить город")
    public ResponseEntity<City> addCity(@RequestParam String cityName) {
        City city = cityService.addCity(new City(cityName));
        return ResponseEntity.ok(city);
    }

    @PutMapping("/update")
    @Operation(
            summary = "Изменить параметры города"
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Параметры изменены", content = {
                    @Content(mediaType = "application/json", array = @ArraySchema(schema = @Schema(implementation = Employee.class)))}),
            @ApiResponse(responseCode = "400", description = "Параметры запроса отсутствуют или имеют некорректный формат"),
            @ApiResponse(responseCode = "404", description = "Такой город не найден"),
            @ApiResponse(responseCode = "500", description = "Произошла ошибка, не зависящая от вызывающей стороны")

    })
    public ResponseEntity<City> updateCity(@RequestBody City city) {
        City updateCity = cityService.updateCity(city);
        if (updateCity == null) {
            ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(updateCity);
    }

    @GetMapping("/getCityById")
    @Operation(
            summary = "Найти город по его уникальному номеру")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Город найден", content = {
                    @Content(mediaType = "application/json", array = @ArraySchema(schema = @Schema(implementation = Employee.class)))}),
            @ApiResponse(responseCode = "400", description = "Параметры запроса отсутствуют или имеют некорректный формат"),
            @ApiResponse(responseCode = "404", description = "Такой город не найден"),
            @ApiResponse(responseCode = "500", description = "Произошла ошибка, не зависящая от вызывающей стороны")

    })
    public ResponseEntity<City> readCityById(@RequestParam int id) {
        City city = cityService.readCityById(id);
        if (city == null) {
            return ResponseEntity.notFound().build();
        } else
            return ResponseEntity.ok(city);
    }

    @DeleteMapping("/deleteCityById")
    @Operation(summary = "Удалить город из БД по его уникальному номеру")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Город удален", content = {
                    @Content(mediaType = "application/json", array = @ArraySchema(schema = @Schema(implementation = Employee.class)))}),
            @ApiResponse(responseCode = "400", description = "Параметры запроса отсутствуют или имеют некорректный формат"),
            @ApiResponse(responseCode = "404", description = "Такой город не найден"),
            @ApiResponse(responseCode = "500", description = "Произошла ошибка, не зависящая от вызывающей стороны")

    })
    public ResponseEntity<Void> deleteCity(@RequestParam int id) {
        if (cityService.deleteCityById(id)) {
            return ResponseEntity.ok().build();
        } else return ResponseEntity.notFound().build();
    }


}
