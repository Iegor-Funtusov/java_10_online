package ua.com.alevel.type;

import lombok.Getter;

@Getter
public enum BrandType {

    APPLE("Apple"),
    DELL("DELL"),
    HP("HP"),
    THINK_PAD("ThinkPad");

    private final String brand;

    BrandType(String brand) {
        this.brand = brand;
    }
}
