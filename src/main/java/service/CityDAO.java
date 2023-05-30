package service;

import model.City;

import java.util.List;

public interface CityDAO {

    City addCity(City city);

    City readCityById(int id);

    List<City> readAllCity();

    City updateCity(City city);

    void deleteCity(City city);
}
