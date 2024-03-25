package ua.com.alevel.dao.impl;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.Query;

import ua.com.alevel.config.JpaConfig;
import ua.com.alevel.config.ObjectFactory;
import ua.com.alevel.dao.DataTableRequest;
import ua.com.alevel.dao.DepartmentDao;
import ua.com.alevel.dto.DepartmentStatistics;
import ua.com.alevel.entity.Department;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Optional;

public class DepartmentDaoImpl implements DepartmentDao {

    private final JpaConfig jpaConfig = ObjectFactory.getInstance().getService(JpaConfig.class);
    private final EntityManager entityManager = jpaConfig.getEntityManagerFactory().createEntityManager();

    @Override
    public void create(Department entity) {
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
    public void update(Department entity) {
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
    public boolean existById(Long id) {
        return false;
    }

    @Override
    public Optional<Department> findById(Long id) {
        return Optional.ofNullable(entityManager.find(Department.class, id));
    }

    @Override
    public Collection<Department> findAll() {
        return entityManager.createQuery("select d from Department d").getResultList();
    }

    @Override
    public Collection<Department> findAll(DataTableRequest request) {
        return null;
    }

    @Override
    public long count() {
        return 0;
    }

    @Override
    public List<DepartmentStatistics> findDepartmentsByNativeQuery() {
        Query query = entityManager.createNativeQuery(
                "select d.id, d.name, count(dep_id) as employee_count from departments as d left join java_10_online.dep_emp de on d.id = de.dep_id group by d.id",
                "DepartmentStatisticsMapping");
        return query.getResultList();
    }

    @Override
    public List<DepartmentStatistics> findDepartmentsByTypedQuery() {
        Query query = entityManager.createQuery(
                "select new ua.com.alevel.dto.DepartmentStatistics(d.id, d.name, count(d.id)) from Department d left join d.employees group by d.id");
        return query.getResultList();
    }
}
