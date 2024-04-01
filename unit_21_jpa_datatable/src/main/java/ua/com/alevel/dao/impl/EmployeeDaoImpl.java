package ua.com.alevel.dao.impl;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.Query;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.collections4.MapUtils;

import ua.com.alevel.config.JpaConfig;
import ua.com.alevel.config.ObjectFactory;
import ua.com.alevel.dao.DataTableRequest;
import ua.com.alevel.dao.DepartmentDao;
import ua.com.alevel.dao.EmployeeDao;
import ua.com.alevel.entity.Department;
import ua.com.alevel.entity.Employee;
import ua.com.alevel.type.OrderType;

import java.util.*;

public class EmployeeDaoImpl implements EmployeeDao {

    private final JpaConfig jpaConfig = ObjectFactory.getInstance().getService(JpaConfig.class);
    private final EntityManager entityManager = jpaConfig.getEntityManagerFactory().createEntityManager();
    private final DepartmentDao departmentDao = ObjectFactory.getInstance().getService(DepartmentDao.class);

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
    public void attachEmployeeToDepartment(Long employeeId, Long departmentId) {
        Employee employee = findById(employeeId).get();
        Department department = departmentDao.findById(departmentId).get();
        Set<Employee> employees = department.getEmployees();

        employees.add(employee);
        departmentDao.update(department);

        // WARNING!!! Not working because Department is owner

//        Set<Department> departments = employee.getDepartments();
//        if (departments != null) {
////            departments.add(department);
//            employee.setDepartments(departments);
//            update(employee);
//        }
    }

    @Override
    public void detachEmployeeToDepartment(Long employeeId, Long departmentId) {
        Department department = departmentDao.findById(departmentId).get();
        Set<Employee> employees = department.getEmployees();
        if (CollectionUtils.isNotEmpty(employees)) {
            employees.removeIf(e -> e.getId().equals(employeeId));
            departmentDao.update(department);
        }
    }

    @Override
    public Collection<Employee> findAllEmployeesByDepartment(Long departmentId) {
        return Collections.emptyList();
    }

    @Override
    public Collection<Employee> findAllEmployeesByNotDepartment(Long departmentId) {
        return Collections.emptyList();
    }

    @Override
    public boolean existById(Long id) {
        return false;
    }

    @Override
    public Collection<Employee> findAll() {
        return entityManager.createQuery("select e from Employee e").getResultList();
    }

    @Override
    public Collection<Employee> findAll(DataTableRequest request) {
        System.out.println("request = " + request);
        // Criteria API

        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<Employee> criteriaQuery = criteriaBuilder.createQuery(Employee.class);
        Root<Employee> from = criteriaQuery.from(Employee.class);

        List<Predicate> predicates = new ArrayList<>();

        if (MapUtils.isNotEmpty(request.getParameters())) {
            request.getParameters().forEach((field, value) -> {
                Predicate predicate = null;
                switch (field) {
                    case "firstName" -> predicate = criteriaBuilder.like(from.get(field), value);
                    case "lastName" -> predicate = criteriaBuilder.like(from.get(field), "%" + value + "%");
                    case "age" -> predicate = criteriaBuilder.equal(from.get(field), value);
                }

                if (predicate != null) {
                    predicates.add(predicate);
                }
            });
        }

        criteriaQuery.where(predicates.toArray(new Predicate[0]));

        if (request.getOrderType().equals(OrderType.ASC)) {
            criteriaQuery.orderBy(criteriaBuilder.asc(from.get(request.getColumn())));
        }
        if (request.getOrderType().equals(OrderType.DESC)) {
            criteriaQuery.orderBy(criteriaBuilder.desc(from.get(request.getColumn())));
        }

        int page = (request.getPage() - 1) * request.getSize();
        int size = request.getSize();
        return entityManager
                .createQuery(criteriaQuery)
                .setFirstResult(page)
                .setMaxResults(size)
                .getResultList();
    }

    @Override
    public long count() {
        return 0;
    }
}
