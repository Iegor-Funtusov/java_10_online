package ua.com.alevel.elastic.repository;

import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.stereotype.Repository;
import ua.com.alevel.elastic.document.QuerySearch;

@Repository
public interface QuerySearchRepository extends ElasticsearchRepository<QuerySearch, String> { }
