package ua.com.alevel.facade;

import ua.com.alevel.dto.request.DataTableRequest;
import ua.com.alevel.dto.response.DataTableResponse;
import ua.com.alevel.dto.response.SavedQueryResponse;

public interface SavedQueryFacade {

    DataTableResponse<SavedQueryResponse> findAll(DataTableRequest request);
}
