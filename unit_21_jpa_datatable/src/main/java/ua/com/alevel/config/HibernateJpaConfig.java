package ua.com.alevel.config;

import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

public class HibernateJpaConfig implements JpaConfig {

    private final EntityManagerFactory entityManagerFactory;

    public HibernateJpaConfig() {
        this.entityManagerFactory = Persistence
                .createEntityManagerFactory("jpa-hibernate-mysql");
    }

    @Override
    public EntityManagerFactory getEntityManagerFactory() {
        return entityManagerFactory;
    }
}
