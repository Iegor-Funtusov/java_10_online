package ua.com.alevel.dto.response;

import lombok.Getter;
import lombok.Setter;
import ua.com.alevel.entity.product.ProductDisplay;
import ua.com.alevel.type.DisplayType;

@Getter
@Setter
public class ProductDisplayResponse extends ApiResponse {

    private String displayResolution;
    private String displayType;
    private String displaySize;
    private Integer brightness;
    private String aspectRatio;
    private String contrastRatio;
    private String colorGamut;
    private Integer hz;
    private Boolean touch;

    public ProductDisplayResponse(ProductDisplay productDisplay) {
        setId(productDisplay.getId());
        setDisplayResolution(productDisplay.getDisplayResolution());
        setDisplayType(productDisplay.getDisplayType().name());
        setDisplaySize(productDisplay.getDisplaySize());
        setBrightness(productDisplay.getBrightness());
        setAspectRatio(productDisplay.getAspectRatio());
        setContrastRatio(productDisplay.getContrastRatio());
        setTouch(productDisplay.getTouch());
        setHz(productDisplay.getHz());
    }
}
