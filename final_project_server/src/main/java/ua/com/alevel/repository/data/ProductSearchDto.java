package ua.com.alevel.repository.data;

import ua.com.alevel.entity.product.Product;
import ua.com.alevel.type.OsType;

public record ProductSearchDto(Product product, String cpu, OsType os) { }
