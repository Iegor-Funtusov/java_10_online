package ua.com.alevel.controller;

import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import ua.com.alevel.dto.request.DataTableRequest;
import ua.com.alevel.dto.response.DataTableResponse;
import ua.com.alevel.dto.response.ProductResponse;
import ua.com.alevel.facade.ProductFacade;

@Tag(name = "Product Listing Page", description = "the plp API with crud operations")
@RestController
@RequestMapping("/api/products")
@AllArgsConstructor
public class PlpController {

    private final ProductFacade productFacade;

    @GetMapping
    public ResponseEntity<DataTableResponse<ProductResponse>> findAll(
            @RequestParam(defaultValue = "1") int page,
            @RequestParam(defaultValue = "10") int size,
            @RequestParam(defaultValue = "id") String sort,
            @RequestParam(defaultValue = "desc") String order) {
        DataTableRequest request = new DataTableRequest(page, size, sort, order);
        return ResponseEntity.ok(productFacade.findAll(request));
    }
}
