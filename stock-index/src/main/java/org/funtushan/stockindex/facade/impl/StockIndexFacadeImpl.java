package org.funtushan.stockindex.facade.impl;

import lombok.RequiredArgsConstructor;
import org.funtushan.stockindex.api.dto.StockIndexViewData;
import org.funtushan.stockindex.docment.StockIndex;
import org.funtushan.stockindex.facade.StockIndexFacade;
import org.funtushan.stockindex.service.StockIndexService;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class StockIndexFacadeImpl implements StockIndexFacade {

    private final StockIndexService stockIndexService;

    @Override
    public Flux<StockIndexViewData> find() {
        return stockIndexService.findAll()
                .collectList()
                .map(list -> list.stream().collect(Collectors.groupingBy(StockIndex::getName)))
                .map(Map::entrySet)
                .map(map -> {
                    Map<String, List<StockIndexViewData>> indexMap = new HashMap<>();
                    for (Map.Entry<String, List<StockIndex>> stringListEntry : map) {
                        List<StockIndex> stockIndexList = stringListEntry.getValue();
                        var currentValue = stockIndexList.getLast().getValue();
                        var max = stockIndexList.stream().mapToDouble(StockIndex::getValue).max().orElse(0.0);
                        var min = stockIndexList.stream().mapToDouble(StockIndex::getValue).min().orElse(0.0);
                        indexMap.put(
                                stringListEntry.getKey(), List.of(new StockIndexViewData(
                                stringListEntry.getKey(),
                                currentValue,
                                max,
                                min
                        )));
                    }
                    return indexMap;
                })
                .map(Map::entrySet)
                .flatMapMany(Flux::fromIterable)
                .map(Map.Entry::getValue)
                .concatMap(Flux::fromIterable);
    }
}
