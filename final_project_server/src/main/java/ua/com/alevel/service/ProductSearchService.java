package ua.com.alevel.service;

import ua.com.alevel.elastic.document.ProductIndex;

import java.util.List;

public interface ProductSearchService {

    List<ProductIndex> searchProducts(String searchText);
}
