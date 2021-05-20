package com.github.wz2cool.elasticsearch.query.builder;

import com.github.wz2cool.elasticsearch.helper.CommonsHelper;
import com.github.wz2cool.elasticsearch.lambda.GetPropertyFunction;
import org.elasticsearch.index.query.QueryBuilder;
import org.elasticsearch.index.query.TermQueryBuilder;

public class TermExtQueryBuilder<T, P extends Comparable> implements ExtQueryBuilder {

    private final TermQueryBuilder termQueryBuilder;

    public TermExtQueryBuilder(GetPropertyFunction<T, P> getPropertyFunc, P value) {
        String propertyName = CommonsHelper.getPropertyName(getPropertyFunc);
        this.termQueryBuilder = new TermQueryBuilder(propertyName, value);
    }

    @Override
    public QueryBuilder build() {
        return this.termQueryBuilder;
    }
}
