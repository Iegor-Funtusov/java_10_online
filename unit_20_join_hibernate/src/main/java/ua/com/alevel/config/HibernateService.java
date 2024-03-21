package ua.com.alevel.config;

import org.hibernate.SessionFactory;

public interface HibernateService {

    SessionFactory getSessionFactory();
}
