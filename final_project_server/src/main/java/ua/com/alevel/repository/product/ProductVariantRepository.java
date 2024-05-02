package ua.com.alevel.repository.product;

import org.springframework.stereotype.Repository;
import ua.com.alevel.entity.product.ProductVariant;
import ua.com.alevel.repository.BaseRepository;

import java.util.List;

@Repository
public interface ProductVariantRepository extends BaseRepository<ProductVariant> {

    List<ProductVariant> findByProductId(Long productId);
}
