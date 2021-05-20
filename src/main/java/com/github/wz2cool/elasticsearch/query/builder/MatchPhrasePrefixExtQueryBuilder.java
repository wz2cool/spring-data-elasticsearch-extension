package com.github.wz2cool.elasticsearch.query.builder;

import com.github.wz2cool.elasticsearch.helper.CommonsHelper;
import com.github.wz2cool.elasticsearch.lambda.GetStringPropertyFunction;
import org.elasticsearch.index.query.MatchPhrasePrefixQueryBuilder;
import org.elasticsearch.index.query.QueryBuilder;

public class MatchPhrasePrefixExtQueryBuilder<T> implements ExtQueryBuilder {

    private final MatchPhrasePrefixQueryBuilder matchPhrasePrefixQueryBuilder;

    public MatchPhrasePrefixExtQueryBuilder(GetStringPropertyFunction<T> getPropertyFunc, String text) {
        String propertyName = CommonsHelper.getPropertyName(getPropertyFunc);
        this.matchPhrasePrefixQueryBuilder = new MatchPhrasePrefixQueryBuilder(propertyName, text);
    }

    @Override
    public QueryBuilder build() {
        return this.matchPhrasePrefixQueryBuilder;
    }
}
