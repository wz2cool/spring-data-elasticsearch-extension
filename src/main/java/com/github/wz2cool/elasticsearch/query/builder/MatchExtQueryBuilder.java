package com.github.wz2cool.elasticsearch.query.builder;

import com.github.wz2cool.elasticsearch.helper.CommonsHelper;
import com.github.wz2cool.elasticsearch.lambda.GetStringPropertyFunction;
import org.elasticsearch.index.query.MatchQueryBuilder;
import org.elasticsearch.index.query.QueryBuilder;

public class MatchExtQueryBuilder<T> implements ExtQueryBuilder {

    private final GetStringPropertyFunction<T> getPropertyFunc;
    private final String text;

    public MatchExtQueryBuilder(GetStringPropertyFunction<T> getPropertyFunc, String text) {
        this.getPropertyFunc = getPropertyFunc;
        this.text = text;
    }

    @Override
    public QueryBuilder build() {
        String propertyName = CommonsHelper.getPropertyName(this.getPropertyFunc);
        return new MatchQueryBuilder(propertyName, this.text);
    }
}
