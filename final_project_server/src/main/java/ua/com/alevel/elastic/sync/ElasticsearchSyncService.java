package ua.com.alevel.elastic.sync;

import lombok.AllArgsConstructor;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import ua.com.alevel.elastic.document.ProductIndex;
import ua.com.alevel.elastic.repository.ProductIndexRepository;
import ua.com.alevel.repository.data.ProductSearchDto;
import ua.com.alevel.repository.product.ProductVariantRepository;

import java.util.Collections;
import java.util.List;

@Service
@AllArgsConstructor
public class ElasticsearchSyncService {

    private final ProductVariantRepository productVariantRepository;
    private final ProductIndexRepository productIndexRepository;

    @Scheduled(cron = "*/10 * * * * *")
    public void sync() {
        System.out.println("ElasticsearchSyncService.sync");
        productIndexRepository.deleteAll();
        List<ProductIndex> indexList = getProductIndexes();
        if (CollectionUtils.isNotEmpty(indexList)) {
            System.out.println("indexList = " + indexList.size());
            productIndexRepository.saveAll(getProductIndexes());
        }
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
