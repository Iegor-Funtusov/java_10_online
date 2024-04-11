package ua.com.alevel.config;

import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

public class HibernateJpaConfig {

    private final EntityManagerFactory entityManagerFactory;
    private static HibernateJpaConfig INSTANCE = new HibernateJpaConfig();

    private HibernateJpaConfig() {
        this.entityManagerFactory = Persistence
                .createEntityManagerFactory("jpa-hibernate-mysql");
    }

    public static HibernateJpaConfig getInstance() {
        return INSTANCE;
    }

    public EntityManagerFactory getEntityManagerFactory() {
        return entityManagerFactory;
    }
}
