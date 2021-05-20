package com.github.wz2cool.elasticsearch.query.builder;

import com.github.wz2cool.elasticsearch.lambda.GetStringPropertyFunction;

public class ExtQueryBuilders<T> {

    public TermExtQueryBuilder<T, String> termExtQuery(GetStringPropertyFunction<T> getPropertyFunc, String value) {
        return new TermExtQueryBuilder<>(getPropertyFunc, value);
    }
}
