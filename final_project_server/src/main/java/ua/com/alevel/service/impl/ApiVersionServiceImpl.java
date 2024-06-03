package ua.com.alevel.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ua.com.alevel.entity.version.ApiVersion;
import ua.com.alevel.repository.version.ApiVersionRepository;
import ua.com.alevel.service.ApiVersionService;

@Service
@RequiredArgsConstructor
public class ApiVersionServiceImpl implements ApiVersionService {

    private final ApiVersionRepository apiVersionRepository;

    @Override
    public String getApiVersion() {
        ApiVersion apiVersion = apiVersionRepository.findTop1ByOrderByIdDesc();
        return apiVersion.getVersion();
    }

    @Override
    public boolean checkApiVersion(String apiVersion) {
        ApiVersion apiVersionEntity = apiVersionRepository.findTop1ByOrderByIdDesc();
        return apiVersionEntity.getVersion().equals(apiVersion);
    }
}
