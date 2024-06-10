package org.funtushan.stockindex.sync;

import lombok.AllArgsConstructor;
import org.funtushan.stockindex.api.dto.StockIndexSyncResult;
import org.funtushan.stockindex.docment.StockIndex;
import org.funtushan.stockindex.repository.StockIndexRepository;
import org.funtushan.stockindex.type.StockIndexType;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

@Service
@AllArgsConstructor
public class StockIndexApiSyncService {

    private final RestTemplate restTemplate;
    private final StockIndexRepository repository;

    @Value("${stockindex.api.list}")
    private List<String> apiList;

    @Scheduled(cron = "*/60 * * * * *")
    public void sync() {
        CompletableFuture<List<StockIndexSyncResult>> futures = syncDataFromApi();
        List<StockIndexSyncResult> results = futures.join();
        List<StockIndex> indexList = results
                .stream()
                .map(res -> {
                    StockIndex stockIndex = new StockIndex();
                    stockIndex.setName(res.name());
                    stockIndex.setValue(res.value());
                    return stockIndex;
                })
                .toList();
        repository.saveAll(indexList).subscribe();
    }

    private CompletableFuture<List<StockIndexSyncResult>> syncDataFromApi() {
        List<CompletableFuture<StockIndexSyncResult>> futures = apiList.stream().map(this::getResultByUrl).toList();
        CompletableFuture<List<StockIndexSyncResult>> aggregate = CompletableFuture.completedFuture(new ArrayList<>());
        for (CompletableFuture<StockIndexSyncResult> future : futures) {
            aggregate = aggregate.thenCompose(list -> {
                try {
                    list.add(future.get());
                } catch (ExecutionException | InterruptedException e) {
                    throw new RuntimeException(e);
                }
                return CompletableFuture.completedFuture(list);
            });
        }
        return aggregate;
    }

    @Async
    protected CompletableFuture<StockIndexSyncResult> getResultByUrl(String url) {
        ResponseEntity<Double> entity = restTemplate.getForEntity(url, Double.class);
        return CompletableFuture.completedFuture(generateStockIndexSyncResult(url, entity.getBody()));
    }

    private StockIndexSyncResult generateStockIndexSyncResult(String url, Double result) {
        if (url.contains("8081")) {
            return new StockIndexSyncResult(StockIndexType.STOCK_INDEX_A.getType(), result);
        } else if (url.contains("8082")) {
            return new StockIndexSyncResult(StockIndexType.STOCK_INDEX_B.getType(), result);
        } else if (url.contains("8083")) {
            return new StockIndexSyncResult(StockIndexType.STOCK_INDEX_C.getType(), result);
        } else {
            throw new RuntimeException("stock not found");
        }
    }
}
