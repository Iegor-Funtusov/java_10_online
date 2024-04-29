package ua.com.alevel.dto.request;

import lombok.Getter;
import lombok.Setter;
import ua.com.alevel.type.BrandType;

@Getter
@Setter
public class ProductRequest extends ApiRequest {
    private Long id;
    private String name;
    private String description;
    private BrandType productBrand;
}
