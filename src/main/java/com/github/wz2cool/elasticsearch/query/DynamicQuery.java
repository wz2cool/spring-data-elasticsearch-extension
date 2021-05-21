package com.github.wz2cool.elasticsearch.query;

import com.github.wz2cool.elasticsearch.core.HighlightResultMapper;
import com.github.wz2cool.elasticsearch.helper.CommonsHelper;
import com.github.wz2cool.elasticsearch.lambda.GetCommonPropertyFunction;
import com.github.wz2cool.elasticsearch.lambda.GetPropertyFunction;
import org.elasticsearch.search.fetch.subphase.highlight.HighlightBuilder;
import org.elasticsearch.search.sort.*;

import java.util.ArrayList;
import java.util.List;
import java.util.function.BiConsumer;

public class DynamicQuery<T> extends BaseFilterGroup<T, DynamicQuery<T>> {

    private final Class<T> clazz;
    private final HighlightResultMapper highlightResultMapper = new HighlightResultMapper();
    private final HighlightBuilder highlightBuilder = new HighlightBuilder();
    private final List<SortBuilder> sortBuilders = new ArrayList<>();
    private int pageSize = 10;

    private DynamicQuery(Class<T> clazz) {
        this.clazz = clazz;
    }

    public static <T> DynamicQuery<T> createQuery(Class<T> clazz) {
        return new DynamicQuery<>(clazz);
    }

    public DynamicQuery<T> scoreMapping(BiConsumer<T, Float> setScorePropertyFunc) {
        highlightResultMapper.registerScoreMapping(this.clazz, setScorePropertyFunc);
        return this;
    }

    public DynamicQuery<T> highlightMapping(GetPropertyFunction<T, String> getSearchPropertyFunc,
                                            BiConsumer<T, String> setHighLightPropertyFunc) {
        String propertyName = CommonsHelper.getPropertyName(getSearchPropertyFunc);
        highlightBuilder.field(propertyName);
        highlightResultMapper.registerHitMapping(this.clazz, getSearchPropertyFunc, setHighLightPropertyFunc);
        return this;
    }

    public DynamicQuery<T> pageSize(int pageSize) {
        this.pageSize = pageSize;
        return this;
    }

    public DynamicQuery<T> orderByScore(SortOrder sortOrder) {
        return orderByScore(true, sortOrder);
    }

    public DynamicQuery<T> orderByScore(boolean enable, SortOrder sortOrder) {
        if (enable) {
            final ScoreSortBuilder order = new ScoreSortBuilder().order(sortOrder);
            this.sortBuilders.add(order);
        }
        return this;
    }

    public DynamicQuery<T> orderBy(GetCommonPropertyFunction<T> getPropertyFunc, SortOrder sortOrder) {
        return orderBy(true, getPropertyFunc, sortOrder);
    }

    public DynamicQuery<T> orderBy(boolean enable, GetCommonPropertyFunction<T> getPropertyFunc, SortOrder sortOrder) {
        if (enable) {
            String propertyName = CommonsHelper.getPropertyName(getPropertyFunc);
            final FieldSortBuilder order = new FieldSortBuilder(propertyName).order(sortOrder);
            this.sortBuilders.add(order);
        }
        return this;
    }

    public HighlightResultMapper getHighlightResultMapper() {
        return highlightResultMapper;
    }

    public HighlightBuilder getHighlightBuilder() {
        return highlightBuilder;
    }

    public List<SortBuilder> getSortBuilders() {
        return sortBuilders;
    }

    public Class<T> getClazz() {
        return clazz;
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }
}
