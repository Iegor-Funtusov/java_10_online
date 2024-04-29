package ua.com.alevel.integration.facade;

import org.apache.commons.collections4.CollectionUtils;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import ua.com.alevel.dto.request.DataTableRequest;
import ua.com.alevel.dto.request.ProductRequest;
import ua.com.alevel.dto.response.DataTableResponse;
import ua.com.alevel.dto.response.ProductResponse;
import ua.com.alevel.facade.ProductFacade;
import ua.com.alevel.type.BrandType;

@SpringBootTest
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class ProductFacadeTest {

    private static final String PRODUCT_NAME = "Product Name";
    private static final String PRODUCT_NAME_UPDATE = "Product Name Update";
    private static final String PRODUCT_DESCRIPTION = "Product Description";

    @Autowired
    private ProductFacade productFacade;

    @Test
    @Order(1)
    public void shouldBeCreateProduct() {
        // given
        ProductRequest request = new ProductRequest();
        request.setName(PRODUCT_NAME);
        request.setDescription(PRODUCT_DESCRIPTION);
        request.setProductBrand(BrandType.APPLE);

        DataTableResponse<ProductResponse> responseBefore = productFacade.findAll(generateDataTableRequest());
        long sizeBefore = responseBefore.getTotalElements();

        // when
        productFacade.create(request);

        // then
        DataTableResponse<ProductResponse> response = productFacade.findAll(generateDataTableRequest());
        Assertions.assertEquals(response.getTotalElements(), sizeBefore + 1);
    }

    @Test
    @Order(2)
    public void shouldBeUpdateProduct() {
        // given
        ProductRequest request = new ProductRequest();
        request.setName(PRODUCT_NAME_UPDATE);
        request.setDescription(PRODUCT_DESCRIPTION);
        request.setProductBrand(BrandType.APPLE);

        DataTableResponse<ProductResponse> responseBefore = productFacade.findAll(generateDataTableRequest());
        Long id = responseBefore.getItems().get(0).getId();

        // when
        productFacade.update(request, id);

        // then
        ProductResponse productResponse = productFacade.findById(id);
        Assertions.assertEquals(productResponse.getName(), PRODUCT_NAME_UPDATE);
    }

    @Test
    @Order(3)
    public void shouldBeFinByIDProduct() {
        // given
        ProductRequest request = new ProductRequest();
        request.setName(PRODUCT_NAME);
        request.setDescription(PRODUCT_DESCRIPTION);
        request.setProductBrand(BrandType.DELL);

        // when
        productFacade.create(request);

        // then
        ProductResponse productResponse = productFacade.findById(2L);
        Assertions.assertNotNull(productResponse);
    }

    @Test
    @Order(4)
    public void shouldBeFinAllProducts() {
        // given
        DataTableRequest request = generateDataTableRequest();

        // when
        DataTableResponse<ProductResponse> response = productFacade.findAll(request);

        // then
        Assertions.assertEquals(response.getTotalElements(), 2);
        Assertions.assertEquals(response.getTotalPages(), 1);
        Assertions.assertTrue(response.isHasContent());
        Assertions.assertTrue(response.isFirst());
        Assertions.assertFalse(response.isNext());
        Assertions.assertTrue(response.isLast());
        Assertions.assertFalse(response.isPrevious());
        Assertions.assertTrue(CollectionUtils.isNotEmpty(response.getItems()));
    }

    @Test
    @Order(5)
    public void shouldBeDeleteProduct() {
        // given
        Long id = 2L;

        DataTableResponse<ProductResponse> responseBefore = productFacade.findAll(generateDataTableRequest());
        long sizeBefore = responseBefore.getTotalElements();

        // when
        productFacade.delete(id);

        // then
        DataTableResponse<ProductResponse> response = productFacade.findAll(generateDataTableRequest());
        Assertions.assertEquals(response.getTotalElements(), sizeBefore - 1);
    }

    private DataTableRequest generateDataTableRequest() {
        DataTableRequest request = new DataTableRequest();
        request.setPage(1);
        request.setSize(10);
        request.setSort("id");
        request.setOrder("asc");
        return request;
    }
}
