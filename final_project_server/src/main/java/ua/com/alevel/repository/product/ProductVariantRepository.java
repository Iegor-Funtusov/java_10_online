package ua.com.alevel.repository.product;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import ua.com.alevel.entity.product.ProductVariant;
import ua.com.alevel.repository.BaseRepository;
import ua.com.alevel.repository.data.ProductSearchDto;

import java.util.List;

@Repository
public interface ProductVariantRepository extends BaseRepository<ProductVariant> {

    List<ProductVariant> findByProductId(Long productId);

    @Query(value = "select distinct new ua.com.alevel.repository.data.ProductSearchDto(pv.product, pv.cpu, pv.os) from ProductVariant pv")
    List<ProductSearchDto> findAllProductSearchDtoList();
}
