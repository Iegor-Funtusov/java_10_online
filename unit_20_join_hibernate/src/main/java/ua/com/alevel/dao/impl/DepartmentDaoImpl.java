package ua.com.alevel.dao.impl;

import jakarta.persistence.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import ua.com.alevel.config.HibernateService;
import ua.com.alevel.config.ObjectFactory;
import ua.com.alevel.dao.DataTableRequest;
import ua.com.alevel.dao.DepartmentDao;
import ua.com.alevel.entity.Department;
import ua.com.alevel.entity.Employee;

import java.util.*;

public class DepartmentDaoImpl implements DepartmentDao {

    private final HibernateService hibernateService = ObjectFactory.getInstance().getService(HibernateService.class);

    @Override
    public void create(Department entity) {
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
    public void update(Department entity) {
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
    public boolean existById(Long id) {
        return false;
    }

    @Override
    public Optional<Department> findById(Long id) {
        Transaction transaction = null;
        try(Session session = hibernateService.getSessionFactory().getCurrentSession()) {
            transaction = session.beginTransaction();
            Department department = session.find(Department.class, id);
            transaction.commit();
            return Optional.of(department);
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
        }
        return Optional.empty();
    }

    @Override
    public Collection<Department> findAll() {
        Transaction transaction = null;
        try(Session session = hibernateService.getSessionFactory().getCurrentSession()) {
            transaction = session.beginTransaction();
            Query query = session.createQuery("from Department");
            List<Department> departments = query.getResultList();
            transaction.commit();
            return departments;
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
        }
        return Collections.emptyList();
    }

    @Override
    public Collection<Department> findAll(DataTableRequest request) {
        return null;
    }

    @Override
    public long count() {
        return 0;
    }
}
