package com.github.wz2cool.elasticsearch.query;

import com.github.wz2cool.elasticsearch.lambda.GetLongPropertyFunction;
import com.github.wz2cool.elasticsearch.model.UpDown;
import org.elasticsearch.index.query.QueryBuilder;
import org.elasticsearch.search.sort.SortOrder;
import org.springframework.data.elasticsearch.core.ResultsMapper;

public class LogicPagingQuery<T> {

    private final Class<T> clazz;
    private final UpDown upDown;
    private final SortOrder sortOrder;
    private final GetLongPropertyFunction<T> pagingPropertyFunc;
    private QueryBuilder queryBuilder;
    private ResultsMapper resultsMapper;
    private int pageSize = 10;
    private Long lastStartPageId;
    private Long lastEndPageId;

    private LogicPagingQuery(Class<T> clazz, GetLongPropertyFunction<T> pagingPropertyFunc, SortOrder sortOrder, UpDown upDown) {
        this.clazz = clazz;
        this.upDown = upDown;
        this.sortOrder = sortOrder;
        this.pagingPropertyFunc = pagingPropertyFunc;
    }

    public static <T> LogicPagingQuery<T> createQuery(
            Class<T> clazz, GetLongPropertyFunction<T> pagingPropertyFunc, SortOrder sortOrder, UpDown upDown) {
        return new LogicPagingQuery<>(clazz, pagingPropertyFunc, sortOrder, upDown);
    }

    public QueryBuilder getQueryBuilder() {
        return queryBuilder;
    }

    public void setQueryBuilder(QueryBuilder queryBuilder) {
        this.queryBuilder = queryBuilder;
    }

    public Class<T> getClazz() {
        return clazz;
    }

    public UpDown getUpDown() {
        return upDown;
    }

    public SortOrder getSortOrder() {
        return sortOrder;
    }

    public GetLongPropertyFunction<T> getPagingPropertyFunc() {
        return pagingPropertyFunc;
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    public Long getLastStartPageId() {
        return lastStartPageId;
    }

    public void setLastStartPageId(Long lastStartPageId) {
        this.lastStartPageId = lastStartPageId;
    }

    public Long getLastEndPageId() {
        return lastEndPageId;
    }

    public void setLastEndPageId(Long lastEndPageId) {
        this.lastEndPageId = lastEndPageId;
    }

    public ResultsMapper getResultsMapper() {
        return resultsMapper;
    }

    public void setResultsMapper(ResultsMapper resultsMapper) {
        this.resultsMapper = resultsMapper;
    }
}


