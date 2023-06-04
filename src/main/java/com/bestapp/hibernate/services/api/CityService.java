package com.bestapp.hibernate.services.api;

import com.bestapp.hibernate.model.City;

import java.util.List;

public interface CityService {

    City addCity(City city);

    City readCityById(int id);

    List<City> readAllCity();

    City updateCity(City city);

    void deleteCity(City city);
}
