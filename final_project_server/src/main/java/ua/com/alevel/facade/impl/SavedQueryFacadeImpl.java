package ua.com.alevel.facade.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import ua.com.alevel.dto.request.DataTableRequest;
import ua.com.alevel.dto.response.DataTableResponse;
import ua.com.alevel.dto.response.SavedQueryResponse;
import ua.com.alevel.facade.SavedQueryFacade;
import ua.com.alevel.service.SavedQueryService;
import ua.com.alevel.store.SavedQuery;

import java.util.List;

@Service
@RequiredArgsConstructor
public class SavedQueryFacadeImpl implements SavedQueryFacade {

    private final SavedQueryService savedQueryService;

    @Override
    public DataTableResponse<SavedQueryResponse> findAll(DataTableRequest request) {
        DataTableResponse<SavedQueryResponse> response = new DataTableResponse<>();
        Page<SavedQuery> page = savedQueryService.findAll(request);
        response.setSort(request.getSort());
        response.setOrder(request.getOrder());
        List<SavedQueryResponse> list = page.getContent().stream().map(SavedQueryResponse::new).toList();
        response.setItems(list);
        response.setNext(page.hasNext());
        response.setLast(page.isLast());
        response.setFirst(page.isFirst());
        response.setHasContent(page.hasContent());
        response.setPrevious(page.hasPrevious());
        response.setTotalElements(page.getTotalElements());
        response.setTotalPages(page.getTotalPages());
        return response;
    }
}
