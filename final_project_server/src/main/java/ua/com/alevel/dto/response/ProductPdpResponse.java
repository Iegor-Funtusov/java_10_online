package ua.com.alevel.dto.response;

import lombok.Getter;
import lombok.Setter;
import org.apache.commons.collections4.CollectionUtils;
import ua.com.alevel.entity.product.Product;
import ua.com.alevel.entity.product.ProductImage;
import ua.com.alevel.entity.product.ProductVariant;

import java.util.List;
import java.util.Set;

@Getter
@Setter
public class ProductPdpResponse extends ApiResponse {

    private String name;
    private String description;
    private String productBrand;
    private Double height;
    private Double width;
    private Double depth;
    private Double weight;
    private List<String> images;
    private List<ProductVariantResponse> variants;

    public ProductPdpResponse(Product product, List<ProductVariant> productVariants) {
        setId(product.getId());
        setName(product.getName());
        setDescription(product.getDescription());
        setProductBrand(product.getProductBrand().getBrand());
        Set<ProductImage> productImages = product.getProductImages();
        if (CollectionUtils.isNotEmpty(productImages)) {
            setImages(productImages.stream().map(ProductImage::getImageUrl).toList());
        }
        ProductVariant pv = productVariants.getFirst();
        setHeight(pv.getHeight());
        setWidth(pv.getWidth());
        setDepth(pv.getDepth());
        setWeight(pv.getWeight());
        this.variants = productVariants.stream().map(ProductVariantResponse::new).toList();
    }
}
