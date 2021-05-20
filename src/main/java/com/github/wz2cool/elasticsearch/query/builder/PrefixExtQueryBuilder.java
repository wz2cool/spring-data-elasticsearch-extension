package com.github.wz2cool.elasticsearch.query.builder;

import com.github.wz2cool.elasticsearch.helper.CommonsHelper;
import com.github.wz2cool.elasticsearch.lambda.GetStringPropertyFunction;
import org.elasticsearch.index.query.PrefixQueryBuilder;
import org.elasticsearch.index.query.QueryBuilder;

public class PrefixExtQueryBuilder<T> implements ExtQueryBuilder {

    private final PrefixQueryBuilder prefixQueryBuilder;

    public PrefixExtQueryBuilder(GetStringPropertyFunction<T> getPropertyFunc, String prefix) {
        String propertyName = CommonsHelper.getPropertyName(getPropertyFunc);
        this.prefixQueryBuilder = new PrefixQueryBuilder(propertyName, prefix);
    }

    @Override
    public QueryBuilder build() {
        return this.prefixQueryBuilder;
    }
}
