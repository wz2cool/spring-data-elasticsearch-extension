package com.github.wz2cool.elasticsearch.query.builder;

import com.github.wz2cool.elasticsearch.helper.CommonsHelper;
import com.github.wz2cool.elasticsearch.lambda.GetStringPropertyFunction;
import org.elasticsearch.index.query.MatchPhraseQueryBuilder;
import org.elasticsearch.index.query.QueryBuilder;

public class MatchPhraseExtQueryBuilder<T> implements ExtQueryBuilder {

    private final MatchPhraseQueryBuilder matchPhraseQueryBuilder;

    public MatchPhraseExtQueryBuilder(GetStringPropertyFunction<T> getPropertyFunc, String text) {
        String propertyName = CommonsHelper.getPropertyName(getPropertyFunc);
        this.matchPhraseQueryBuilder = new MatchPhraseQueryBuilder(propertyName, text);
    }

    @Override
    public QueryBuilder build() {
        return this.matchPhraseQueryBuilder;
    }
}
