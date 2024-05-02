package ua.com.alevel.service;

import ua.com.alevel.entity.product.ProductVariant;

import java.util.List;

public interface ProductVariantService {

    List<ProductVariant> findByProductId(Long productId);
}
