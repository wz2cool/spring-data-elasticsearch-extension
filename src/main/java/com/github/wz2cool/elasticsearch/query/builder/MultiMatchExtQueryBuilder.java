package com.github.wz2cool.elasticsearch.query.builder;

import com.github.wz2cool.elasticsearch.helper.CommonsHelper;
import com.github.wz2cool.elasticsearch.lambda.GetStringPropertyFunction;
import org.elasticsearch.index.query.MultiMatchQueryBuilder;
import org.elasticsearch.index.query.QueryBuilder;

public class MultiMatchExtQueryBuilder<T> implements ExtQueryBuilder {

    private final MultiMatchQueryBuilder multiMatchQueryBuilder;

    public MultiMatchExtQueryBuilder(String value, GetStringPropertyFunction<T>... getPropertyFuncs) {
        this.multiMatchQueryBuilder = new MultiMatchQueryBuilder(value);
        for (GetStringPropertyFunction<T> getPropertyFunc : getPropertyFuncs) {
            field(getPropertyFunc);
        }
    }

    public MultiMatchExtQueryBuilder<T> field(GetStringPropertyFunction<T> getPropertyFunc) {
        String propertyName = CommonsHelper.getPropertyName(getPropertyFunc);
        this.multiMatchQueryBuilder.field(propertyName);
        return this;
    }

    public MultiMatchExtQueryBuilder<T> field(GetStringPropertyFunction<T> getPropertyFunc, float boost) {
        String propertyName = CommonsHelper.getPropertyName(getPropertyFunc);
        this.multiMatchQueryBuilder.field(propertyName, boost);
        return this;
    }

    @Override
    public QueryBuilder build() {
        return this.multiMatchQueryBuilder;
    }
}
