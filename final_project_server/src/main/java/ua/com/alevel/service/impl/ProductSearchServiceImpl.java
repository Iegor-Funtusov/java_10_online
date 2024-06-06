package ua.com.alevel.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ua.com.alevel.elastic.document.ProductIndex;
import ua.com.alevel.elastic.repository.ProductIndexRepository;
import ua.com.alevel.service.ProductSearchService;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductSearchServiceImpl implements ProductSearchService {

    private final ProductIndexRepository productIndexRepository;

    @Override
    public List<ProductIndex> searchProducts(String searchText) {
        return productIndexRepository.findAllByProductInfoContainingIgnoreCase(searchText);
    }
}
