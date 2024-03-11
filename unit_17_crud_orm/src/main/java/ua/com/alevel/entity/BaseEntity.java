package ua.com.alevel.entity;

import ua.com.alevel.annotation.Column;
import ua.com.alevel.annotation.Id;

public abstract class BaseEntity {

    @Id
    @Column
    private Long id;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
