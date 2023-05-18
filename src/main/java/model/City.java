package model;

import lombok.Data;

@Data
public class City {

    private String cityName;
    private int cityId;

    public City(String cityName, int cityId) {
        this.cityName = cityName;
        this.cityId = cityId;
    }

    public City(int cityId) {
        this.cityId = cityId;
    }

    @Override
    public String toString() {
        return cityName;
    }
}
