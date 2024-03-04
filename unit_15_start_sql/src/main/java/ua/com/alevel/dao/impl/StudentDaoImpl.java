package ua.com.alevel.dao.impl;

import ua.com.alevel.dao.StudentDao;
import ua.com.alevel.entity.Student;

import java.sql.*;
import java.util.*;
import java.util.Date;

public class StudentDaoImpl implements StudentDao {

    private final Connection connection;
//    PreparedStatement;
//    Statement;

    public StudentDaoImpl() {
        try {
            connection = DriverManager.getConnection(
                    "jdbc:mysql://localhost:3306/java_10_online",
                    "root",
                    "Test123!");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void create(Student entity) {
        try (
                PreparedStatement ps = connection.prepareStatement("insert into students values (default, current_timestamp(), ?, ?, ?)")
        ) {
            ps.setString(1, entity.getFirstName());
            ps.setString(2, entity.getLastName());
            ps.setInt(3, entity.getAge());
            ps.executeUpdate();
        } catch (SQLException e) {
            System.out.println("create not working: " + e.getMessage());
        }
    }

    @Override
    public void update(Student entity) {
        try (
                PreparedStatement ps = connection.prepareStatement("update students set first_name = ?, last_name = ?, age = ? where id = ?")
        ) {
            ps.setString(1, entity.getFirstName());
            ps.setString(2, entity.getLastName());
            ps.setInt(3, entity.getAge());
            ps.setLong(4, entity.getId());
            ps.executeUpdate();
        } catch (SQLException e) {
            System.out.println("update not working: " + e.getMessage());
        }
    }

    @Override
    public void delete(Long id) {
        try (
                PreparedStatement ps = connection.prepareStatement("delete from students where id = ?")
        ) {
            ps.setLong(1, id);
            ps.executeUpdate();
        } catch (SQLException e) {
            System.out.println("delete not working: " + e.getMessage());
        }
    }

    @Override
    public boolean existById(Long id) {
        try (
                Statement statement = connection.createStatement();
                ResultSet resultSet = statement.executeQuery("select count(id) as count from students where id = " + id)
        ) {
            while (resultSet.next()) {
                return resultSet.getLong("count") == 1;
            }
        } catch (SQLException e) {
            System.out.println("findById not working: " + e.getMessage());
            return false;
        }

        return false;
    }

    @Override
    public Optional<Student> findById(Long id) {
        try (
                Statement statement = connection.createStatement();
                ResultSet resultSet = statement.executeQuery("select * from students where id = " + id)
        ) {
            while (resultSet.next()) {
                return Optional.of(buildStudentByResultSet(resultSet));
            }
        } catch (SQLException e) {
            System.out.println("findById not working: " + e.getMessage());
            return Optional.empty();
        }
        return Optional.empty();
    }

    @Override
    public Collection<Student> findAll() {
        List<Student> students = new ArrayList<>();
        try (
                Statement statement = connection.createStatement();
                ResultSet resultSet = statement.executeQuery("select * from students")
        ) {
            while (resultSet.next()) {
                students.add(buildStudentByResultSet(resultSet));
            }
        } catch (SQLException e) {
            System.out.println("findAll not working: " + e.getMessage());
            return Collections.emptyList();
        }
        return students;
    }

    @Override
    public long count() {
        try (
                Statement statement = connection.createStatement();
                ResultSet resultSet = statement.executeQuery("select count(id) as count from students")
        ) {
            while (resultSet.next()) {
                return resultSet.getLong("count");
//                return resultSet.getLong("count(id)");
            }
        } catch (SQLException e) {
            System.out.println("findAll not working: " + e.getMessage());
            return 0;
        }
        return 0;
    }

    private Student buildStudentByResultSet(ResultSet resultSet) throws SQLException {
        Long id = resultSet.getLong("id");
        Date created = resultSet.getTimestamp("created");
        String firstName = resultSet.getString("first_name");
        String lastName = resultSet.getString("last_name");
        int age = resultSet.getInt("age");
        Student student = new Student();
        student.setId(id);
        student.setCreated(created);
        student.setFirstName(firstName);
        student.setLastName(lastName);
        student.setAge(age);
        return student;
    }
}
