package ua.com.alevel.repository.product;

import org.springframework.stereotype.Repository;
import ua.com.alevel.entity.product.Product;
import ua.com.alevel.repository.BaseRepository;

@Repository
public interface ProductRepository extends BaseRepository<Product> {
}
