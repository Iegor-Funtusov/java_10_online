package org.funtushan.stockindex.facade;

import org.funtushan.stockindex.api.dto.StockIndexViewData;
import reactor.core.publisher.Flux;

public interface StockIndexFacade {

    Flux<StockIndexViewData> find();
}
