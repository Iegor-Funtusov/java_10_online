package ua.com.alevel.entity.version;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import ua.com.alevel.entity.BaseEntity;

import java.util.Date;

@Getter
@Setter
@Entity
@Table(name = "api_versions")
public class ApiVersion extends BaseEntity {

    @Column(nullable = false, unique = true)
    private String version;

    @Temporal(TemporalType.TIMESTAMP)
    private Date created;

    public ApiVersion() {
        this.created = new Date();
    }
}
