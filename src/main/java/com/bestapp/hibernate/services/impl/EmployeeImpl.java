package com.bestapp.hibernate.services.impl;

import com.bestapp.hibernate.services.api.EmployeeService;
import com.bestapp.hibernate.model.Employee;
import com.bestapp.hibernate.utils.HibernateSessionFactoryUtil;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class EmployeeImpl implements EmployeeService {

    @Override
    public Employee addEmployee(Employee employee) {
        try (Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession()) {
            Transaction transaction = session.beginTransaction();
            session.save(employee);
            transaction.commit();
        }
        return employee;
    }

    @Override
    public Employee readEmployeeById(int id){
        try (Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession()){
            return session.get(Employee.class, id);
        }
    }

    @Override
    public List<Employee> readAllEmployee() {
        try (Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession()) {
            return session.createQuery("FROM Employee ").getResultList();
        }
    }

    @Override
    public Employee updateEmployee(Employee employee) {
        Employee updated;
        try (Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession()) {
            Transaction transaction = session.beginTransaction();
            updated = (Employee) session.merge(employee);
            transaction.commit();
        }
        return updated;
    }

    @Override
    public void deleteEmployee(Employee employee) {
        try (Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession()) {
            Transaction transaction = session.beginTransaction();
            session.delete(employee);
            transaction.commit();
        }
    }
}
