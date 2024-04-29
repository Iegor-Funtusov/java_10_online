package ua.com.alevel.facade;

import ua.com.alevel.dto.request.ApiRequest;
import ua.com.alevel.dto.request.DataTableRequest;
import ua.com.alevel.dto.response.ApiResponse;
import ua.com.alevel.dto.response.DataTableResponse;

public interface CrudFacade<REQ extends ApiRequest, RES extends ApiResponse> {

    void create(REQ request);
    void update(REQ request, Long id);
    void delete(Long id);
    RES findById(Long id);
    DataTableResponse<RES> findAll(DataTableRequest request);
}
