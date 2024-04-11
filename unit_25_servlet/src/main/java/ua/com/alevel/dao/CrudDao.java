package ua.com.alevel.dao;

import ua.com.alevel.entity.BaseEntity;

import java.util.Collection;
import java.util.Optional;

public interface CrudDao<E extends BaseEntity> {

    void create(E entity);
    void update(E entity);
    void delete(Long id);
    Optional<E> findById(Long id);
    Collection<E> findAll();
}
