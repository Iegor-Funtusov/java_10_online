package ua.com.alevel.entity;

import java.util.Date;

public abstract class BaseEntity {

    private Long id;
    private Date created;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Date getCreated() {
        return created;
    }

    public void setCreated(Date created) {
        this.created = created;
    }
}
