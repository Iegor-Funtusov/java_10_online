package ua.com.alevel.dao.impl;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.Query;

import ua.com.alevel.config.HibernateJpaConfig;
import ua.com.alevel.dao.DepartmentDao;
import ua.com.alevel.entity.Department;

import java.util.Collection;
import java.util.Optional;

public class DepartmentDaoImpl implements DepartmentDao {

    private final EntityManager entityManager = HibernateJpaConfig
            .getInstance()
            .getEntityManagerFactory()
            .createEntityManager();

    @Override
    public void create(Department entity) {
        System.out.println("Department = " + entity);
        EntityTransaction transaction = null;
        try {
            transaction = entityManager.getTransaction();
            transaction.begin();
            entityManager.persist(entity);
            transaction.commit();
        } catch (Exception e) {
            System.out.println("e = " + e);
            if (transaction != null) {
                transaction.rollback();
            }
        }
    }

    @Override
    public void update(Department entity) {
        EntityTransaction transaction = null;
        try {
            transaction = entityManager.getTransaction();
            transaction.begin();
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
            transaction.begin();
            Query query = entityManager.createQuery("delete from Department d where d.id = :id")
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
    public Optional<Department> findById(Long id) {
        return Optional.ofNullable(entityManager.find(Department.class, id));
    }

    @Override
    public Collection<Department> findAll() {
        return entityManager.createQuery("select d from Department d").getResultList();
    }
}
