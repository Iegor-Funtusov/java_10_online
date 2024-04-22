package ua.com.alevel.config;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import lombok.Getter;

@Getter
public class HibernateJpaConfig {

    private final EntityManager entityManager;

    public HibernateJpaConfig() {
        final EntityManagerFactory entityManagerFactory = Persistence
                .createEntityManagerFactory("jpa-hibernate-mysql");
        this.entityManager = entityManagerFactory.createEntityManager();
    }
}
