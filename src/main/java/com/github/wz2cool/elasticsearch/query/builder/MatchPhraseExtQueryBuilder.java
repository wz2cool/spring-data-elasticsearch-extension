package com.github.wz2cool.elasticsearch.query.builder;

import com.github.wz2cool.elasticsearch.helper.CommonsHelper;
import com.github.wz2cool.elasticsearch.lambda.GetStringPropertyFunction;
import org.elasticsearch.index.query.MatchPhraseQueryBuilder;
import org.elasticsearch.index.query.QueryBuilder;

/**
 * Match query is a query that analyzes the text and constructs a phrase query
 * as the result of the analysis.
 *
 * @author Frank
 */
public class MatchPhraseExtQueryBuilder<T> implements ExtQueryBuilder {

    private final MatchPhraseQueryBuilder matchPhraseQueryBuilder;

    /**
     * see also {@link MatchPhraseQueryBuilder#MatchPhraseQueryBuilder(String, Object)}
     */
    public MatchPhraseExtQueryBuilder(GetStringPropertyFunction<T> getPropertyFunc, String text) {
        String propertyName = CommonsHelper.getPropertyName(getPropertyFunc);
        this.matchPhraseQueryBuilder = new MatchPhraseQueryBuilder(propertyName, text);
    }

    /**
     * see also {@link MatchPhraseQueryBuilder#analyzer(String)}
     */
    public MatchPhraseExtQueryBuilder<T> analyzer(String analyzer) {
        this.matchPhraseQueryBuilder.analyzer(analyzer);
        return this;
    }

    /**
     * see also {@link MatchPhraseQueryBuilder#slop(int)}
     */
    public MatchPhraseExtQueryBuilder<T> slop(int slop) {
        this.matchPhraseQueryBuilder.slop(slop);
        return this;
    }

    @Override
    public QueryBuilder build() {
        return this.matchPhraseQueryBuilder;
    }
}
