package ua.com.alevel.service;

import ua.com.alevel.entity.BaseEntity;

public interface CrudService<E extends BaseEntity> {

    void create(E entity);
    E findById(Long id);
}
