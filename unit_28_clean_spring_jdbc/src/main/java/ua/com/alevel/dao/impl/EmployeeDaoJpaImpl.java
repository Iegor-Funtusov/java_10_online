package ua.com.alevel.dao.impl;

import jakarta.persistence.EntityManager;
import ua.com.alevel.config.HibernateJpaConfig;
import ua.com.alevel.dao.EmployeeDao;
import ua.com.alevel.entity.Employee;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

public class EmployeeDaoJpaImpl implements EmployeeDao {

    private final EntityManager em;
    private final HibernateJpaConfig hibernateJpaConfig;

    public EmployeeDaoJpaImpl(HibernateJpaConfig hibernateJpaConfig) {
        this.hibernateJpaConfig = hibernateJpaConfig;
        this.em = hibernateJpaConfig.getEntityManager();
    }

    @Override
    public void attachEmployeeToDepartment(Long employeeId, Long departmentId) {

    }

    @Override
    public void detachEmployeeToDepartment(Long employeeId, Long departmentId) {

    }

    @Override
    public Collection<Employee> findAllEmployeesByDepartment(Long departmentId) {
        return List.of();
    }

    @Override
    public Collection<Employee> findAllEmployeesByNotDepartment(Long departmentId) {
        return List.of();
    }

    @Override
    public void create(Employee entity) {

    }

    @Override
    public void update(Employee entity) {

    }

    @Override
    public void delete(Long id) {

    }

    @Override
    public boolean existById(Long id) {
        return false;
    }

    @Override
    public Optional<Employee> findById(Long id) {
        return Optional.empty();
    }

    @Override
    public Collection<Employee> findAll() {
        return em.createQuery("select e from Employee e").getResultList();
    }

    @Override
    public long count() {
        return 0;
    }
}
