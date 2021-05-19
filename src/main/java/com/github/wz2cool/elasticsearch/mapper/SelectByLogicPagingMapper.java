package com.github.wz2cool.elasticsearch.mapper;

import com.github.wz2cool.elasticsearch.core.HighlightResultMapper;
import com.github.wz2cool.elasticsearch.helper.LogicPagingHelper;
import com.github.wz2cool.elasticsearch.model.LogicPagingResult;
import com.github.wz2cool.elasticsearch.model.SortDescriptor;
import com.github.wz2cool.elasticsearch.model.UpDown;
import com.github.wz2cool.elasticsearch.query.LogicPagingQuery;
import org.elasticsearch.index.query.BoolQueryBuilder;
import org.elasticsearch.index.query.QueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.sort.SortBuilders;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.elasticsearch.core.ElasticsearchOperations;
import org.springframework.data.elasticsearch.core.query.NativeSearchQueryBuilder;
import org.springframework.util.CollectionUtils;

import java.util.*;

import static com.github.wz2cool.elasticsearch.helper.CommonsHelper.getPropertyName;

/**
 * @author Frank
 **/
public interface SelectByLogicPagingMapper<T> {

    /**
     * select by logic paging
     *
     * @param elasticsearchOperations elasticsearch operations
     * @param logicPagingQuery        logic paging query
     * @return logic paging result
     */
    default LogicPagingResult<T> selectByLogicPaging(ElasticsearchOperations elasticsearchOperations, LogicPagingQuery<T> logicPagingQuery) {
        int pageSize = logicPagingQuery.getPageSize();
        int queryPageSize = pageSize + 1;
        final BoolQueryBuilder boolQueryBuilder = QueryBuilders.boolQuery();
        Map.Entry<SortDescriptor, QueryBuilder> mapEntry = LogicPagingHelper.getPagingSortFilterMap(
                logicPagingQuery.getPagingPropertyFunc(),
                logicPagingQuery.getSortOrder(),
                logicPagingQuery.getLastStartPageId(),
                logicPagingQuery.getLastEndPageId(),
                logicPagingQuery.getUpDown());
        if (Objects.nonNull(logicPagingQuery.getQueryBuilder())) {
            boolQueryBuilder.must(logicPagingQuery.getQueryBuilder());
        }
        if (Objects.nonNull(mapEntry.getValue())) {
            boolQueryBuilder.must(mapEntry.getValue());
        }
        NativeSearchQueryBuilder esQuery = new NativeSearchQueryBuilder();
        esQuery.withQuery(boolQueryBuilder);
        esQuery.withPageable(PageRequest.of(0, queryPageSize));
        esQuery.withSort(SortBuilders.fieldSort(getPropertyName(logicPagingQuery.getPagingPropertyFunc()))
                .order(logicPagingQuery.getSortOrder()));
        esQuery.withHighlightBuilder(logicPagingQuery.getHighlightBuilder());
        Page<T> ts;
        final HighlightResultMapper highlightResultMapper = logicPagingQuery.getHighlightResultMapper();
        if (Objects.nonNull(highlightResultMapper)
                && !CollectionUtils.isEmpty(highlightResultMapper.getPropertyMapping(logicPagingQuery.getClazz()))) {
            ts = elasticsearchOperations.queryForPage(
                    esQuery.build(), logicPagingQuery.getClazz(), logicPagingQuery.getHighlightResultMapper());
        } else {
            ts = elasticsearchOperations.queryForPage(
                    esQuery.build(), logicPagingQuery.getClazz());
        }
        List<T> dataList = new ArrayList<>(ts.getContent());
        if (!logicPagingQuery.getSortOrder().equals(mapEntry.getKey().getSortOrder())) {
            Collections.reverse(dataList);
        }
        Optional<LogicPagingResult<T>> logicPagingResultOptional = LogicPagingHelper.getPagingResult(
                logicPagingQuery.getPagingPropertyFunc(),
                dataList, logicPagingQuery.getPageSize(), logicPagingQuery.getUpDown());
        if (logicPagingResultOptional.isPresent()) {
            return logicPagingResultOptional.get();
        }
        LogicPagingQuery<T> resetPagingQuery = LogicPagingQuery.createQuery(
                logicPagingQuery.getClazz(),
                logicPagingQuery.getPagingPropertyFunc(),
                logicPagingQuery.getSortOrder(),
                UpDown.NONE);
        resetPagingQuery.setPageSize(logicPagingQuery.getPageSize());
        resetPagingQuery.setQueryBuilder(logicPagingQuery.getQueryBuilder());
        resetPagingQuery.setHighlightBuilder(logicPagingQuery.getHighlightBuilder());
        resetPagingQuery.setHighlightResultMapper(logicPagingQuery.getHighlightResultMapper());
        return selectByLogicPaging(elasticsearchOperations, resetPagingQuery);
    }
}
