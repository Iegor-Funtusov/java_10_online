package ua.com.alevel.dto.response;

import lombok.Getter;
import lombok.Setter;
import ua.com.alevel.entity.product.ProductVariant;

@Getter
@Setter
public class ProductVariantResponse extends ApiResponse {

    private String os;
    private String cpu;
    private Integer ram;
    private Integer ssd;
    private String color;
    private String battery;
    private String camera;
    private String price;
    private String wireless;
    private ProductDisplayResponse productDisplay;

    public ProductVariantResponse(ProductVariant productVariant) {
        setId(productVariant.getId());
        this.os = productVariant.getOs().getType();
        this.cpu = productVariant.getCpu();
        this.ram = productVariant.getRam();
        this.ssd = productVariant.getSsd();
        this.color = productVariant.getColor();
        this.battery = productVariant.getBattery();
        this.camera = productVariant.getCamera();
        this.price = productVariant.getPrice().toString();
        this.wireless = productVariant.getWireless();
        if (productVariant.getProductDisplay() != null) {
            this.productDisplay = new ProductDisplayResponse(productVariant.getProductDisplay());
        }
    }
}
