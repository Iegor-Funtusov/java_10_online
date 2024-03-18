package ua.com.alevel;

import jakarta.persistence.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import ua.com.alevel.config.HibernateConfig;
import ua.com.alevel.entity.Student;

import java.util.Collections;
import java.util.List;

public class HibernateCrud {

    private final HibernateConfig hibernateConfig = new HibernateConfig();

    public void test() {
//        Student student = generateStudent();
//        create(student);

//        Student student = find2(3L);
//        student.setAge(21);
//        update1(student);

//        delete1(student);
//        delete2(student);

        for (Student student : findAll()) {
            System.out.println("student = " + student);
        }
    }

    private void create(Student student) {
        Transaction transaction = null;
        try {
            Session session = hibernateConfig.getSessionFactory().getCurrentSession();
            transaction = session.beginTransaction();
            session.save(student);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
        }
    }

    private void update1(Student student) {
        Transaction transaction = null;
        try {
            Session session = hibernateConfig.getSessionFactory().getCurrentSession();
            transaction = session.beginTransaction();
            session.update(student);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
        }
    }

    private void update2(Student student) {
        Transaction transaction = null;
        try {
            Session session = hibernateConfig.getSessionFactory().getCurrentSession();
            transaction = session.beginTransaction();
            Query query = session.createQuery("update Student s set s.age = :age, s.firstName")
                    .setParameter("studentId", student.getId());
            query.executeUpdate();
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
        }
    }

    private Student find1(Long id) {
        Transaction transaction = null;
        try(Session session = hibernateConfig.getSessionFactory().getCurrentSession()) {
            transaction = session.beginTransaction();
            Student student = session.find(Student.class, id);
            transaction.commit();
            return student;
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
        }
        return null;
    }

    // HQL
    private Student find2(Long id) {
        Transaction transaction = null;
        try(Session session = hibernateConfig.getSessionFactory().getCurrentSession()) {
            transaction = session.beginTransaction();
            Query query = session.createQuery("from Student s where s.id = :studentId")
                    .setParameter("studentId", id);
            Student student = (Student) query.getResultList().get(0);
            transaction.commit();
            return student;
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
        }
        return null;
    }

    private void delete1(Student student) {
        Transaction transaction = null;
        try {
            Session session = hibernateConfig.getSessionFactory().getCurrentSession();
            transaction = session.beginTransaction();
            session.delete(student);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
        }
    }

    private void delete2(Student student) {
        Transaction transaction = null;
        try {
            Session session = hibernateConfig.getSessionFactory().getCurrentSession();
            transaction = session.beginTransaction();
            Query query = session.createQuery("delete from Student s where s.id = :id")
                    .setParameter("id", student.getId());
            query.executeUpdate();
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
        }
    }

    private List<Student> findAll() {
        Transaction transaction = null;
        try {
            Session session = hibernateConfig.getSessionFactory().getCurrentSession();
            transaction = session.beginTransaction();
            Query query = session.createQuery("from Student");
            List<Student> students = query.getResultList();
            transaction.commit();
            return students;
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
        }
        return Collections.emptyList();
    }

    private Student generateStudent() {
        Student student = new Student();
        student.setEmail("student3@gmail.com");
        student.setFirstName("ee");
        student.setLastName("eee");
        student.setAge(20);
        return student;
    }
}
