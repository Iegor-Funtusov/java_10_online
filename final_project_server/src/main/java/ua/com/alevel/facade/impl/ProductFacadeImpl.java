package ua.com.alevel.facade.impl;

import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import ua.com.alevel.dto.request.DataTableRequest;
import ua.com.alevel.dto.request.ProductRequest;
import ua.com.alevel.dto.response.DataTableResponse;
import ua.com.alevel.dto.response.ProductResponse;
import ua.com.alevel.entity.product.Product;
import ua.com.alevel.facade.ProductFacade;
import ua.com.alevel.service.ProductService;

import java.util.List;

@Service
@AllArgsConstructor
public class ProductFacadeImpl implements ProductFacade {

    private final ProductService productService;

    @Override
    public void create(ProductRequest request) {
        Product product = new Product();
        product.setName(request.getName());
        product.setDescription(request.getDescription());
        product.setProductBrand(request.getProductBrand());
        productService.create(product);
    }

    @Override
    public void update(ProductRequest request, Long id) {
        Product product = productService.findById(id);
        product.setName(request.getName());
        product.setDescription(request.getDescription());
        productService.update(product);
    }

    @Override
    public void delete(Long id) {
        productService.delete(id);
    }

    @Override
    public ProductResponse findById(Long id) {
        return new ProductResponse(productService.findById(id));
    }

    @Override
    public DataTableResponse<ProductResponse> findAll(DataTableRequest request) {
        Page<Product> page = productService.findAll(request);
        DataTableResponse<ProductResponse> dataTableResponse = new DataTableResponse<>(page);
        dataTableResponse.setSort(request.getSort());
        dataTableResponse.setOrder(request.getOrder());
        List<ProductResponse> productResponseList = page.getContent().stream().map(ProductResponse::new).toList();
        dataTableResponse.setItems(productResponseList);
        return dataTableResponse;
    }
}
