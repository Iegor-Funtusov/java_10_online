package ua.com.alevel.config;

import java.sql.Connection;

public interface JdbcService {

    Connection getConnection();
}
