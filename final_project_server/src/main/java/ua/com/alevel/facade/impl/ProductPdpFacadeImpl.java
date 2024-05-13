package ua.com.alevel.facade.impl;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import ua.com.alevel.dto.response.ProductPdpResponse;
import ua.com.alevel.entity.product.Product;
import ua.com.alevel.entity.product.ProductVariant;
import ua.com.alevel.facade.ProductPdpFacade;
import ua.com.alevel.service.ProductService;
import ua.com.alevel.service.ProductVariantService;

import java.util.List;

@Service
@AllArgsConstructor
public class ProductPdpFacadeImpl implements ProductPdpFacade {

    private final ProductService productService;
    private final ProductVariantService productVariantService;
    @Override
    public ProductPdpResponse findByProduct(Long productId) {
        Product product = productService.findById(productId);
        List<ProductVariant> productVariants = productVariantService.findByProductId(productId);
        return new ProductPdpResponse(product, productVariants);
    }
}
