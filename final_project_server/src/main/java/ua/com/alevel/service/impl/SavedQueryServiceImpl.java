package ua.com.alevel.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import ua.com.alevel.dto.request.DataTableRequest;
import ua.com.alevel.service.SavedQueryService;
import ua.com.alevel.store.SavedQuery;
import ua.com.alevel.store.SavedQueryRepository;

@Service
@RequiredArgsConstructor
public class SavedQueryServiceImpl implements SavedQueryService {

    private final SavedQueryRepository savedQueryRepository;

    @Override
    public void save(String searchText) {
        SavedQuery savedQuery = new SavedQuery(searchText);
        savedQueryRepository.save(savedQuery);
    }

    @Override
    public Page<SavedQuery> findAll(DataTableRequest request) {
        Sort sort = Sort.by(
                request.getOrder().equals("asc") ? Sort.Direction.ASC : Sort.Direction.DESC,
                request.getSort());
        Pageable pageable = PageRequest.of(request.getPage() - 1, request.getSize(), sort);
        return savedQueryRepository.findAll(pageable);
    }
}
