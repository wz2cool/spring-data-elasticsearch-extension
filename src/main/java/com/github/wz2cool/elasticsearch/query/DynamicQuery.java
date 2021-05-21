package com.github.wz2cool.elasticsearch.query;

import com.github.wz2cool.elasticsearch.core.HighlightResultMapper;
import com.github.wz2cool.elasticsearch.helper.CommonsHelper;
import com.github.wz2cool.elasticsearch.lambda.GetPropertyFunction;
import org.elasticsearch.search.fetch.subphase.highlight.HighlightBuilder;

import java.util.function.BiConsumer;

public class DynamicQuery<T> extends BaseFilterGroup<T, DynamicQuery<T>> {

    private final Class<T> clazz;
    private final HighlightResultMapper highlightResultMapper = new HighlightResultMapper();
    private final HighlightBuilder highlightBuilder = new HighlightBuilder();

    public DynamicQuery(Class<T> clazz) {
        this.clazz = clazz;
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

    public HighlightResultMapper getHighlightResultMapper() {
        return highlightResultMapper;
    }

    public HighlightBuilder getHighlightBuilder() {
        return highlightBuilder;
    }
}
