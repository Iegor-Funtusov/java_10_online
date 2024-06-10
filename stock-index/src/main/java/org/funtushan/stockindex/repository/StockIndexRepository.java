package org.funtushan.stockindex.repository;

import org.funtushan.stockindex.docment.StockIndex;
import org.springframework.data.elasticsearch.repository.ReactiveElasticsearchRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StockIndexRepository extends ReactiveElasticsearchRepository<StockIndex, String> { }
