package ua.com.alevel.controller;

import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.constraints.Min;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import ua.com.alevel.dto.request.DataTableRequest;
import ua.com.alevel.dto.response.DataTableResponse;
import ua.com.alevel.dto.response.ProductPdpResponse;
import ua.com.alevel.dto.response.ProductResponse;
import ua.com.alevel.dto.response.ResponseContainer;
import ua.com.alevel.elastic.document.ProductIndex;
import ua.com.alevel.facade.ProductFacade;
import ua.com.alevel.facade.ProductPdpFacade;
import ua.com.alevel.service.ProductSearchService;

import java.util.List;

@Tag(name = "Product Page", description = "the plp API with crud operations")
@Validated
@RestController
@RequestMapping("/api/products")
@AllArgsConstructor
public class ProductController {

    private final ProductFacade productFacade;
    private final ProductPdpFacade productPdpFacade;
    private final ProductSearchService productSearchService;

    @GetMapping
    public ResponseEntity<ResponseContainer<DataTableResponse<ProductResponse>>> findAll(
            @RequestParam(defaultValue = "1") int page,
            @RequestParam(defaultValue = "10") int size,
            @RequestParam(defaultValue = "id") String sort,
            @RequestParam(defaultValue = "desc") String order) {
        DataTableRequest request = new DataTableRequest(page, size, sort, order);
        return ResponseEntity.ok(new ResponseContainer<>(productFacade.findAll(request)));
    }

    @GetMapping("/{productId}")
    public ResponseEntity<ResponseContainer<ProductPdpResponse>> findById(@Min(1) @PathVariable Long productId) {
        return ResponseEntity.ok(new ResponseContainer<>(productPdpFacade.findByProduct(productId)));
    }

    @GetMapping("/search")
    public ResponseEntity<ResponseContainer<List<ProductIndex>>> search(@RequestParam String search) {
        return ResponseEntity.ok(new ResponseContainer<>(productSearchService.searchProducts(search)));
    }
}
