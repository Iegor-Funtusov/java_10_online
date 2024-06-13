package ua.com.alevel.elastic.sync;

import lombok.AllArgsConstructor;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.collections4.IterableUtils;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import ua.com.alevel.elastic.document.ProductIndex;
import ua.com.alevel.elastic.document.QuerySearch;
import ua.com.alevel.elastic.repository.ProductIndexRepository;
import ua.com.alevel.elastic.repository.QuerySearchRepository;
import ua.com.alevel.logger.LoggerLevel;
import ua.com.alevel.logger.LoggerService;
import ua.com.alevel.repository.data.ProductSearchDto;
import ua.com.alevel.repository.product.ProductVariantRepository;
import ua.com.alevel.store.SavedQuery;
import ua.com.alevel.store.SavedQueryRepository;

import java.util.*;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class ElasticsearchSyncService {

    private final ProductVariantRepository productVariantRepository;
    private final ProductIndexRepository productIndexRepository;
    private final QuerySearchRepository querySearchRepository;
    private final SavedQueryRepository savedQueryRepository;
    private final LoggerService loggerService;

    @Scheduled(cron = "*/30 * * * * *")
    public void sync() {
        System.out.println("ElasticsearchSyncService.sync");
        productIndexRepository.deleteAll();
        List<ProductIndex> indexList = getProductIndexes();
        if (CollectionUtils.isNotEmpty(indexList)) {
            loggerService.log(LoggerLevel.INFO, "indexList size: " + indexList.size());
            productIndexRepository.saveAll(getProductIndexes());
        }
    }

    @Scheduled(cron = "*/10 * * * * *")
    public void findAllQuerySearchAndMoveToMongo() {
        Iterable<QuerySearch> querySearchCollection = querySearchRepository.findAll();
        Set<String> querySearches = IterableUtils.toList(querySearchCollection)
                .stream()
                .map(QuerySearch::getQuery)
                .collect(Collectors.toSet());
        List<SavedQuery> savedQueries = querySearches.stream().map(SavedQuery::new).toList();
        savedQueryRepository.saveAll(savedQueries);
        querySearchRepository.deleteAll();
    }

    private List<ProductIndex> getProductIndexes() {
        List<ProductSearchDto> productSearchDtoList = productVariantRepository.findAllProductSearchDtoList();
        if (CollectionUtils.isNotEmpty(productSearchDtoList)) {
            return productSearchDtoList
                    .stream()
                    .map(ps -> ProductIndex
                            .builder()
                            .productId(ps.product().getId())
                            .productInfo(ps.product().getName() + ", " + ps.cpu() + ", " + ps.os().getType())
                            .build())
                    .toList();
        }
        return Collections.emptyList();
    }
}
