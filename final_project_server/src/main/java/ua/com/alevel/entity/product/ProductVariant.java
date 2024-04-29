package ua.com.alevel.entity.product;

import jakarta.persistence.*;
import jakarta.validation.constraints.Digits;
import lombok.Getter;
import lombok.Setter;
import ua.com.alevel.entity.BaseEntity;
import ua.com.alevel.type.OsType;

import java.math.BigDecimal;

@Getter
@Setter
@Entity
@Table(name = "product_variants")
public class ProductVariant extends BaseEntity {

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private OsType os;

    @Column(nullable = false)
    private String cpu;

    @Column(nullable = false)
    private Integer ram;

    @Column(nullable = false)
    private Integer ssd;

    @Column(nullable = false)
    private String color;

    @Column(nullable = false)
    private String battery;

    @Column(nullable = false)
    private String camera;

    @Column(nullable = false)
    @Digits(integer = 6, fraction = 2)
    private BigDecimal price;

    @Column(nullable = false)
    private String wireless;

    @Column(nullable = false)
    private Double height;

    @Column(nullable = false)
    private Double width;

    @Column(nullable = false)
    private Double depth;

    @Column(nullable = false)
    private Double weight;

    @ManyToOne
    private Product product;

    @ManyToOne
    private ProductDisplay productDisplay;
}
