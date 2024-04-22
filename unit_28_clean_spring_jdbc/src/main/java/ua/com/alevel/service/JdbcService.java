package ua.com.alevel.service;

import java.sql.Connection;

public interface JdbcService {

    Connection getConnection();
}
