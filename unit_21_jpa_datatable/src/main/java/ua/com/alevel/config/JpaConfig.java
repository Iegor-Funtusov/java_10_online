package ua.com.alevel.config;

import jakarta.persistence.EntityManagerFactory;

public interface JpaConfig {

    EntityManagerFactory getEntityManagerFactory();
}
