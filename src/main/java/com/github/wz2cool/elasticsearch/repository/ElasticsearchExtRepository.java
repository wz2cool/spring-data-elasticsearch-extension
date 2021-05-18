package com.github.wz2cool.elasticsearch.repository;

import com.github.wz2cool.elasticsearch.mapper.SelectByLogicPagingMapper;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

/**
 * Abstract Elasticsearch Repository
 *
 * @param <T> entity class
 * @author Frank
 */
public interface ElasticsearchExtRepository<T, I> extends
        ElasticsearchRepository<T, I>,
        SelectByLogicPagingMapper<T> {
}
