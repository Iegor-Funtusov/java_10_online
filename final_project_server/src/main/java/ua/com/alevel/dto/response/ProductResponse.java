package ua.com.alevel.dto.response;

import lombok.Getter;
import lombok.Setter;
import org.apache.commons.collections4.CollectionUtils;
import ua.com.alevel.entity.product.Product;
import ua.com.alevel.entity.product.ProductImage;

import java.util.Set;

@Getter
@Setter
public class ProductResponse extends ApiResponse {
    private String name;
    private String description;
    private String productBrand;
    private String productImage;

    public ProductResponse(Product product) {
        setId(product.getId());
        this.name = product.getName();
        this.description = product.getDescription();
        this.productBrand = product.getProductBrand().getBrand();
        Set<ProductImage> productImages = product.getProductImages();
        if (CollectionUtils.isNotEmpty(productImages)) {
            this.productImage = productImages
                    .stream()
                    .filter(ProductImage::getMainImage)
                    .findFirst()
                    .get()
                    .getImageUrl();
        }
    }
}
