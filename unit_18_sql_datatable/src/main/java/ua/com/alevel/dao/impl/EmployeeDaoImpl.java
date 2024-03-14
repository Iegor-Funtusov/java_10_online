package ua.com.alevel.dao.impl;

import ua.com.alevel.config.JdbcService;
import ua.com.alevel.config.ObjectFactory;
import ua.com.alevel.dao.DataTableRequest;
import ua.com.alevel.dao.EmployeeDao;
import ua.com.alevel.entity.Employee;
import ua.com.alevel.type.OrderType;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.*;

public class EmployeeDaoImpl implements EmployeeDao {

    private final JdbcService jdbcService = ObjectFactory.getInstance().getService(JdbcService.class);

    @Override
    public void create(Employee entity) {
        try (
                PreparedStatement ps = jdbcService.getConnection().prepareStatement("insert into employees values (default, ?, ?, ?)")
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
    public void update(Employee entity) {

    }

    @Override
    public void delete(Long id) {

    }

    @Override
    public void attachEmployeeToDepartment(Long employeeId, Long departmentId) {
        try (
                PreparedStatement ps = jdbcService.getConnection().prepareStatement("insert into dep_emp values (?, ?)")
        ) {
            ps.setLong(1, departmentId);
            ps.setLong(2, employeeId);
            ps.executeUpdate();
        } catch (SQLException e) {
            System.out.println("attachEmployeeToDepartment not working: " + e.getMessage());
        }
    }

    @Override
    public void detachEmployeeToDepartment(Long employeeId, Long departmentId) {
        try (
                PreparedStatement ps = jdbcService.getConnection().prepareStatement("delete from dep_emp where dep_id = ? and emp_id = ?")
        ) {
            ps.setLong(1, departmentId);
            ps.setLong(2, employeeId);
            ps.executeUpdate();
        } catch (SQLException e) {
            System.out.println("attachEmployeeToDepartment not working: " + e.getMessage());
        }
    }

    @Override
    public Collection<Employee> findAllEmployeesByDepartment(Long departmentId) {
        List<Employee> employees = new ArrayList<>();
        try (
                Statement statement = jdbcService.getConnection().createStatement();
                ResultSet resultSet = statement.executeQuery("select emp.id, emp.first_name, emp.last_name, emp.age from employees as emp left join dep_emp as de on emp.id = de.emp_id where dep_id = " + departmentId)
        ) {
            while (resultSet.next()) {
                employees.add(buildEmployeeByResultSet(resultSet));
            }
        } catch (SQLException e) {
            System.out.println("findAll not working: " + e.getMessage());
            return Collections.emptyList();
        }
        return employees;
    }

    @Override
    public Collection<Employee> findAllEmployeesByNotDepartment(Long departmentId) {
        List<Employee> employees = new ArrayList<>();
        try(PreparedStatement preparedStatement = jdbcService.getConnection().prepareStatement("select id, first_name, last_name, age from employees where id not in (select id from employees left join dep_emp de on de.emp_id = employees.id where de.dep_id = ?)")) {
            preparedStatement.setLong(1, departmentId);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                employees.add(buildEmployeeByResultSet(resultSet));
            }
        } catch (SQLException e) {
            return employees;
        }
        return employees;
    }

    @Override
    public boolean existById(Long id) {
        return false;
    }

    @Override
    public Optional<Employee> findById(Long id) {
        try (
                Statement statement = jdbcService.getConnection().createStatement();
                ResultSet resultSet = statement.executeQuery("select * from employees where id = " + id)
        ) {
            while (resultSet.next()) {
                return Optional.of(buildEmployeeByResultSet(resultSet));
            }
        } catch (SQLException e) {
            System.out.println("findById not working: " + e.getMessage());
            return Optional.empty();
        }
        return Optional.empty();
    }

    @Override
    public Collection<Employee> findAll() {
        List<Employee> employees = new ArrayList<>();
        try (
                Statement statement = jdbcService.getConnection().createStatement();
                ResultSet resultSet = statement.executeQuery("select * from employees")
        ) {
            while (resultSet.next()) {
                employees.add(buildEmployeeByResultSet(resultSet));
            }
        } catch (SQLException e) {
            System.out.println("findAll not working: " + e.getMessage());
            return Collections.emptyList();
        }
        return employees;
    }

    @Override
    public Collection<Employee> findAll(DataTableRequest request) {
        List<Employee> employees = new ArrayList<>();
        StringBuilder queryBuilder = new StringBuilder("select * from employees order by ");
        queryBuilder.append(request.getColumn());
        if (request.getOrderType().equals(OrderType.DESC)) {
            queryBuilder.append(" ");
            queryBuilder.append("desc");
        }
        queryBuilder.append(" ");
        queryBuilder.append("limit");
        queryBuilder.append(" ");
        int page = (request.getPage() - 1) * request.getSize();
        int size = request.getSize();
        queryBuilder.append(page);
        queryBuilder.append(",");
        queryBuilder.append(size);
        String query = queryBuilder.toString();
        System.out.println("query = " + query);
        try (
                Statement statement = jdbcService.getConnection().createStatement();
                ResultSet resultSet = statement.executeQuery(query)
        ) {
            while (resultSet.next()) {
                employees.add(buildEmployeeByResultSet(resultSet));
            }
        } catch (SQLException e) {
            System.out.println("findAll not working: " + e.getMessage());
            return Collections.emptyList();
        }
        return employees;
    }

    @Override
    public long count() {
        return 0;
    }

    private Employee buildEmployeeByResultSet(ResultSet resultSet) throws SQLException {
        Long id = resultSet.getLong("id");
        String firstName = resultSet.getString("first_name");
        String lastName = resultSet.getString("last_name");
        int age = resultSet.getInt("age");
        Employee employee = new Employee();
        employee.setId(id);
        employee.setFirstName(firstName);
        employee.setLastName(lastName);
        employee.setAge(age);
        return employee;
    }
}
