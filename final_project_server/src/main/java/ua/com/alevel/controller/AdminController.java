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
import ua.com.alevel.dto.response.SavedQueryResponse;
import ua.com.alevel.dto.response.ResponseContainer;
import ua.com.alevel.facade.SavedQueryFacade;

@Tag(name = "Admin controller", description = "contains admin methods")
@RestController
@RequestMapping("/api/admin")
@AllArgsConstructor
public class AdminController {

    private final SavedQueryFacade savedQueryFacade;

    @GetMapping("products/search/review")
    public ResponseEntity<ResponseContainer<DataTableResponse<SavedQueryResponse>>> searchReview(
            @RequestParam(defaultValue = "1") int page,
            @RequestParam(defaultValue = "10") int size,
            @RequestParam(defaultValue = "id") String sort,
            @RequestParam(defaultValue = "desc") String order
    ) {
        DataTableRequest request = new DataTableRequest(page, size, sort, order);
        return ResponseEntity.ok(new ResponseContainer<>(savedQueryFacade.findAll(request)));
    }
}
