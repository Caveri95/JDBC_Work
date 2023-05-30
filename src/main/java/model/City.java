package model;

import lombok.Data;

import javax.persistence.*;
import java.util.List;

@Data
@Entity
@Table(name = "city")
public class City {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "city_id")
    private int cityId;

    @Column(name = "city_name")
    private String cityName;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "city")
    private List<Employee> employees;

    public City(String cityName, int cityId) {
        this.cityName = cityName;
        this.cityId = cityId;
    }

    public City(String cityName) {
        this.cityName = cityName;
    }

    public City(int cityId) {
        this.cityId = cityId;
    }

    public City() {

    }

    @Override
    public String toString() {
        return cityName;
    }
}
