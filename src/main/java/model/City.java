package model;

import lombok.Data;

@Data
public class City {

    private String city_name;
    private int city_id;

    public City(String city_name, int city_id) {
        this.city_name = city_name;
        this.city_id = city_id;
    }

    public City(int city_id) {
        this.city_id = city_id;
    }

    @Override
    public String toString() {
        return city_name;
    }
}
