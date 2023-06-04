package com.bestapp.hibernate.services.impl;

import com.bestapp.hibernate.services.api.CityService;
import com.bestapp.hibernate.model.City;
import com.bestapp.hibernate.utils.HibernateSessionFactoryUtil;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class CityServiceImpl implements CityService {
    @Override
    public City addCity(City city) {
        try (Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession()) {
            Transaction transaction = session.beginTransaction();
            session.save(city);
            transaction.commit();
        }
        return city;
    }

    @Override
    public City readCityById(int id) {
        try (Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession()){
            return session.get(City.class, id);
        }
    }

    @Override
    public List<City> readAllCity() {
        try (Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession()) {
            return session.createQuery("FROM City").getResultList();
        }

    }

    @Override
    public City updateCity(City city) {
        City updated;
        try (Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession()) {
            Transaction transaction = session.beginTransaction();
            updated = (City) session.merge(city);
            transaction.commit();
        }
        return updated;

    }

    @Override
    public void deleteCity(City city) {
        try (Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession()) {
            Transaction transaction = session.beginTransaction();
            session.delete(city);
            transaction.commit();
        }
    }
}
