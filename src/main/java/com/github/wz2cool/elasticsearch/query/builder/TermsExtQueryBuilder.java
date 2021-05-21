package com.github.wz2cool.elasticsearch.query.builder;

import com.github.wz2cool.elasticsearch.helper.CommonsHelper;
import com.github.wz2cool.elasticsearch.lambda.GetPropertyFunction;
import org.elasticsearch.index.query.QueryBuilder;
import org.elasticsearch.index.query.TermsQueryBuilder;

import java.util.Arrays;

public class TermsExtQueryBuilder<T, R extends Comparable> implements ExtQueryBuilder {

    private TermsQueryBuilder termsQueryBuilder;

    @SafeVarargs
    public TermsExtQueryBuilder(GetPropertyFunction<T, R> getPropertyFunc, R... values) {
        String propertyName = CommonsHelper.getPropertyName(getPropertyFunc);
        this.termsQueryBuilder = new TermsQueryBuilder(propertyName, Arrays.asList(values));
    }

    @Override
    public QueryBuilder build() {
        return this.termsQueryBuilder;
    }
}
