package ua.com.alevel.service;

import org.springframework.data.domain.Page;
import ua.com.alevel.dto.request.DataTableRequest;
import ua.com.alevel.store.SavedQuery;

public interface SavedQueryService {

    void save(String searchText);
    Page<SavedQuery> findAll(DataTableRequest request);
}
