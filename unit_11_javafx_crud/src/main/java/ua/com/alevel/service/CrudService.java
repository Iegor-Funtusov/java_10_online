package ua.com.alevel.service;

import ua.com.alevel.entity.BaseEntity;

import java.util.Collection;

public interface CrudService<E extends BaseEntity> {

    void create(E entity);
    void update(E entity);
    void delete(String id);
    E findById(String id);
    Collection<E> findAll();
}
