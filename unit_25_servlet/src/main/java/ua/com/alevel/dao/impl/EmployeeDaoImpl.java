package ua.com.alevel.dao.impl;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.Query;

import ua.com.alevel.dao.DepartmentDao;
import ua.com.alevel.dao.EmployeeDao;
import ua.com.alevel.entity.Employee;

import java.util.*;

public class EmployeeDaoImpl implements EmployeeDao {

    private  EntityManager entityManager;
    private  DepartmentDao departmentDao;

    @Override
    public void create(Employee entity) {
        EntityTransaction transaction = null;
        try {
            transaction = entityManager.getTransaction();
            entityManager.persist(entity);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
        }
    }

    @Override
    public void update(Employee entity) {
        EntityTransaction transaction = null;
        try {
            transaction = entityManager.getTransaction();
            entityManager.merge(entity);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
        }
    }

    @Override
    public void delete(Long id) {
        EntityTransaction transaction = null;
        try {
            transaction = entityManager.getTransaction();
            Query query = entityManager.createQuery("delete from Employee e where e.id = :id")
                    .setParameter("id", id);
            query.executeUpdate();
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
        }
    }

    @Override
    public Optional<Employee> findById(Long id) {
        return Optional.ofNullable(entityManager.find(Employee.class, id));
    }

    @Override
    public Collection<Employee> findAll() {
        return entityManager.createQuery("select e from Employee e", Employee.class).getResultList();
    }
}
