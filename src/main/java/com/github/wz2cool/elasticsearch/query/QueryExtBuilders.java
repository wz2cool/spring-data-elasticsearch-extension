package com.github.wz2cool.elasticsearch.query;

import com.github.wz2cool.elasticsearch.helper.CommonsHelper;
import com.github.wz2cool.elasticsearch.lambda.*;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.index.query.TermQueryBuilder;

public class QueryExtBuilders<T> {

    private final Class<T> clazz;

    private QueryExtBuilders(Class<T> clazz) {
        this.clazz = clazz;
    }

    public static <T> QueryExtBuilders<T> create(Class<T> clazz) {
        return new QueryExtBuilders<>(clazz);
    }



    /// region termQuery

    public TermQueryBuilder termQueryBuilder(GetStringPropertyFunction<T> getPropertyFunc, String value) {
        String propertyName = CommonsHelper.getPropertyName(getPropertyFunc);
        return QueryBuilders.termQuery(propertyName, value);
    }

    public TermQueryBuilder termQueryBuilder(GetIntegerPropertyFunction<T> getPropertyFunc, int value) {
        String propertyName = CommonsHelper.getPropertyName(getPropertyFunc);
        return QueryBuilders.termQuery(propertyName, value);
    }

    public TermQueryBuilder termQueryBuilder(GetLongPropertyFunction<T> getPropertyFunc, long value) {
        String propertyName = CommonsHelper.getPropertyName(getPropertyFunc);
        return QueryBuilders.termQuery(propertyName, value);
    }

    public TermQueryBuilder termQueryBuilder(GetFloatPropertyFunction<T> getPropertyFunc, float value) {
        String propertyName = CommonsHelper.getPropertyName(getPropertyFunc);
        return QueryBuilders.termQuery(propertyName, value);
    }

    public TermQueryBuilder termQueryBuilder(GetDoublePropertyFunction<T> getPropertyFunc, double value) {
        String propertyName = CommonsHelper.getPropertyName(getPropertyFunc);
        return QueryBuilders.termQuery(propertyName, value);
    }

    public TermQueryBuilder termQueryBuilder(GetBooleanPropertyFunction<T> getPropertyFunc, boolean value) {
        String propertyName = CommonsHelper.getPropertyName(getPropertyFunc);
        return QueryBuilders.termQuery(propertyName, value);
    }

    /// endregion
}
