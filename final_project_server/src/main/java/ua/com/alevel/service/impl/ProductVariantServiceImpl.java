package ua.com.alevel.service.impl;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import ua.com.alevel.entity.product.ProductVariant;
import ua.com.alevel.repository.product.ProductVariantRepository;
import ua.com.alevel.service.ProductVariantService;

import java.util.List;

@Service
@AllArgsConstructor
public class ProductVariantServiceImpl implements ProductVariantService {

    private final ProductVariantRepository productVariantRepository;

    @Override
    public List<ProductVariant> findByProductId(Long productId) {
        return productVariantRepository.findByProductId(productId);
    }
}
