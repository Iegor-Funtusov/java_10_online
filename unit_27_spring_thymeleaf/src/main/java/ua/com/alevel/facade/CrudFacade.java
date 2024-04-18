package ua.com.alevel.facade;

import ua.com.alevel.dto.request.ApiRequest;
import ua.com.alevel.dto.response.ApiResponse;

import java.util.Collection;

public interface CrudFacade<REQ extends ApiRequest, RES extends ApiResponse> {

    void create(REQ request);
    void update(REQ request, Long id);
    void delete(Long id);
    RES findById(Long id);
    Collection<RES> findAll();
}
