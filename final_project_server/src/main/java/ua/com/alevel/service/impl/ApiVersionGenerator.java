package ua.com.alevel.service.impl;

//import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ua.com.alevel.config.processor.annotations.InitMethod;
import ua.com.alevel.entity.version.ApiVersion;
import ua.com.alevel.repository.version.ApiVersionRepository;

import java.util.List;
import java.util.Set;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ApiVersionGenerator {

    private final ApiVersionRepository apiVersionRepository;

//    @PostConstruct
    @InitMethod
    public void generate() {
        List<ApiVersion> apiVersions = apiVersionRepository.findAll();
        ApiVersion apiVersion = new ApiVersion();
        if (apiVersions.isEmpty()) {
            apiVersion.setVersion(UUID.randomUUID().toString());
        } else {
            Set<String> versions = apiVersions
                    .stream()
                    .map(ApiVersion::getVersion)
                    .collect(Collectors.toSet());
            apiVersion.setVersion(generateApiVersion(versions));
        }
        apiVersionRepository.save(apiVersion);
    }

    private String generateApiVersion(final Set<String> apiVersions) {
        String apiVersion = UUID.randomUUID().toString();
        if (apiVersions.stream().anyMatch(apiVersion::equals)) {
            return generateApiVersion(apiVersions);
        }
        return apiVersion;
    }
}
