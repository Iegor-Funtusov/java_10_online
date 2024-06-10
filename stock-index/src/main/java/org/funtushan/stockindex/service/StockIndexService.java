package org.funtushan.stockindex.service;

import org.funtushan.stockindex.docment.StockIndex;
import reactor.core.publisher.Flux;

public interface StockIndexService {

    Flux<StockIndex> findAll();
}
