package ua.com.alevel.facade;

import ua.com.alevel.dto.response.ProductPdpResponse;

public interface ProductPdpFacade {

    ProductPdpResponse findByProduct(Long productId);
}
