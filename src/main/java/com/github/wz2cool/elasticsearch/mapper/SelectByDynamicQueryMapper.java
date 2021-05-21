package com.github.wz2cool.elasticsearch.mapper;

import com.github.wz2cool.elasticsearch.query.DynamicQuery;
import org.elasticsearch.search.sort.SortBuilder;
import org.springframework.data.domain.Page;
import org.springframework.data.elasticsearch.core.ElasticsearchOperations;
import org.springframework.data.elasticsearch.core.query.NativeSearchQueryBuilder;

import java.util.ArrayList;
import java.util.List;

@SuppressWarnings("java:S3740")
public interface SelectByDynamicQueryMapper<T> {

    default List<T> selectByDynamicQuery(ElasticsearchOperations elasticsearchOperations, DynamicQuery<T> dynamicQuery) {
        NativeSearchQueryBuilder esQuery = new NativeSearchQueryBuilder();
        esQuery.withQuery(dynamicQuery.buildQuery());
        for (SortBuilder sortBuilder : dynamicQuery.getSortBuilders()) {
            esQuery.withSort(sortBuilder);
        }
        esQuery.withHighlightBuilder(dynamicQuery.getHighlightBuilder());
        Page<T> ts = elasticsearchOperations.queryForPage(
                esQuery.build(), dynamicQuery.getClazz(), dynamicQuery.getHighlightResultMapper());
        return new ArrayList<>(ts.getContent());
    }
}
