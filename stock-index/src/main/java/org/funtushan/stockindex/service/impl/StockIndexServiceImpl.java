package org.funtushan.stockindex.service.impl;

import lombok.RequiredArgsConstructor;
import org.funtushan.stockindex.docment.StockIndex;
import org.funtushan.stockindex.repository.StockIndexRepository;
import org.funtushan.stockindex.service.StockIndexService;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;

@Service
@RequiredArgsConstructor
public class StockIndexServiceImpl implements StockIndexService {

    private final StockIndexRepository stockIndexRepository;

    @Override
    public Flux<StockIndex> findAll() {
        return stockIndexRepository.findAll();
    }
}
