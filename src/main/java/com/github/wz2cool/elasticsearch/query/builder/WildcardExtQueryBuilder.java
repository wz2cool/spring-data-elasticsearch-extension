package com.github.wz2cool.elasticsearch.query.builder;

import com.github.wz2cool.elasticsearch.helper.CommonsHelper;
import com.github.wz2cool.elasticsearch.lambda.GetStringPropertyFunction;
import org.elasticsearch.index.query.QueryBuilder;
import org.elasticsearch.index.query.WildcardQueryBuilder;

public class WildcardExtQueryBuilder<T> implements ExtQueryBuilder {

    private final WildcardQueryBuilder wildcardQueryBuilder;

    public WildcardExtQueryBuilder(GetStringPropertyFunction<T> getPropertyFunc, String query) {
        String propertyName = CommonsHelper.getPropertyName(getPropertyFunc);
        this.wildcardQueryBuilder = new WildcardQueryBuilder(propertyName, query);
    }

    @Override
    public QueryBuilder build() {
        return this.wildcardQueryBuilder;
    }
}
