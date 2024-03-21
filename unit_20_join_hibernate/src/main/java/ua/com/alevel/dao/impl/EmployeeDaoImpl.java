package ua.com.alevel.dao.impl;

import jakarta.persistence.Query;
import org.apache.commons.collections4.CollectionUtils;
import org.hibernate.Session;
import org.hibernate.Transaction;
import ua.com.alevel.config.HibernateService;
import ua.com.alevel.config.ObjectFactory;
import ua.com.alevel.dao.DataTableRequest;
import ua.com.alevel.dao.DepartmentDao;
import ua.com.alevel.dao.EmployeeDao;
import ua.com.alevel.entity.Department;
import ua.com.alevel.entity.Employee;

import java.util.*;

public class EmployeeDaoImpl implements EmployeeDao {

    private final HibernateService hibernateService = ObjectFactory.getInstance().getService(HibernateService.class);
    private final DepartmentDao departmentDao = ObjectFactory.getInstance().getService(DepartmentDao.class);

    @Override
    public void create(Employee entity) {
        Transaction transaction = null;
        try (Session session = hibernateService.getSessionFactory().getCurrentSession()) {
            transaction = session.beginTransaction();
            session.save(entity);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
        }
    }

    @Override
    public void update(Employee entity) {
        Transaction transaction = null;
        try (Session session = hibernateService.getSessionFactory().getCurrentSession()) {
            transaction = session.beginTransaction();
            session.update(entity);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
        }
    }

    @Override
    public void delete(Long id) {
        Transaction transaction = null;
        try (Session session = hibernateService.getSessionFactory().getCurrentSession()) {
            transaction = session.beginTransaction();
            Query query = session.createQuery("delete from Employee e where e.id = :id")
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
        Transaction transaction = null;
        try(Session session = hibernateService.getSessionFactory().getCurrentSession()) {
            transaction = session.beginTransaction();
            Employee employee = session.find(Employee.class, id);
            transaction.commit();
            return Optional.of(employee);
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
        }
        return Optional.empty();
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
        Transaction transaction = null;
        try(Session session = hibernateService.getSessionFactory().getCurrentSession()) {
            transaction = session.beginTransaction();
            Query query = session.createQuery("from Employee");
            List<Employee> students = query.getResultList();
            transaction.commit();
            return students;
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
        }
        return Collections.emptyList();
    }

    @Override
    public Collection<Employee> findAll(DataTableRequest request) {
        return Collections.emptyList();
    }

    @Override
    public long count() {
        return 0;
    }
}
