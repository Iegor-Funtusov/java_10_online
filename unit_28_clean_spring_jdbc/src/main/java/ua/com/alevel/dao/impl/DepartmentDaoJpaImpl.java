package ua.com.alevel.dao.impl;

import jakarta.persistence.EntityManager;
import ua.com.alevel.config.HibernateJpaConfig;
import ua.com.alevel.dao.DepartmentDao;
import ua.com.alevel.entity.Department;

import java.util.Collection;
import java.util.Optional;

public class DepartmentDaoJpaImpl implements DepartmentDao {

    private final EntityManager em;
    private final HibernateJpaConfig hibernateJpaConfig;

    public DepartmentDaoJpaImpl(HibernateJpaConfig hibernateJpaConfig) {
        this.hibernateJpaConfig = hibernateJpaConfig;
        this.em = hibernateJpaConfig.getEntityManager();
    }

    @Override
    public void create(Department entity) {

    }

    @Override
    public void update(Department entity) {

    }

    @Override
    public void delete(Long id) {

    }

    @Override
    public boolean existById(Long id) {
        return false;
    }

    @Override
    public Optional<Department> findById(Long id) {
        return Optional.empty();
    }

    @Override
    public Collection<Department> findAll() {
        return em.createQuery("select d from Department d").getResultList();
    }

    @Override
    public long count() {
        return 0;
    }
}
