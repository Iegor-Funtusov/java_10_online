package ua.com.alevel.dao.impl;

import ua.com.alevel.annotations.BeanClass;
import ua.com.alevel.annotations.InjectBean;
import ua.com.alevel.config.JdbcService;
import ua.com.alevel.dao.DepartmentDao;
import ua.com.alevel.entity.Department;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.*;

@BeanClass
public class DepartmentDaoImpl implements DepartmentDao {

    @InjectBean
    private JdbcService jdbcService;

    @Override
    public void create(Department entity) {
        try (
                PreparedStatement ps = jdbcService.getConnection().prepareStatement("insert into departments values (default, ?)")
        ) {
            ps.setString(1, entity.getName());
            ps.executeUpdate();
        } catch (SQLException e) {
            System.out.println("create not working: " + e.getMessage());
        }
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
        List<Department> departments = new ArrayList<>();
        try (
                Statement statement = jdbcService.getConnection().createStatement();
                ResultSet resultSet = statement.executeQuery("select * from departments")
        ) {
            while (resultSet.next()) {
                departments.add(buildDepartmentByResultSet(resultSet));
            }
        } catch (SQLException e) {
            System.out.println("findAll not working: " + e.getMessage());
            return Collections.emptyList();
        }
        return departments;
    }

    @Override
    public long count() {
        return 0;
    }

    private Department buildDepartmentByResultSet(ResultSet resultSet) throws SQLException {
        Long id = resultSet.getLong("id");
        String name = resultSet.getString("name");
        Department department = new Department();
        department.setId(id);
        department.setName(name);
        return department;
    }
}
