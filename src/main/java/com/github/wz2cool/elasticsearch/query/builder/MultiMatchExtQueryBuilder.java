package com.github.wz2cool.elasticsearch.query.builder;

import com.github.wz2cool.elasticsearch.helper.CommonsHelper;
import com.github.wz2cool.elasticsearch.lambda.GetStringPropertyFunction;
import org.elasticsearch.index.query.MultiMatchQueryBuilder;
import org.elasticsearch.index.query.QueryBuilder;

public class MultiMatchExtQueryBuilder<T> implements ExtQueryBuilder {

    private final MultiMatchQueryBuilder multiMatchQueryBuilder;

    @SafeVarargs
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

    public MultiMatchExtQueryBuilder<T> minimumShouldMatch(String minimumShouldMatch) {
        this.multiMatchQueryBuilder.minimumShouldMatch(minimumShouldMatch);
        return this;
    }

    public MultiMatchExtQueryBuilder<T> analyzer(String analyzer) {
        this.multiMatchQueryBuilder.analyzer(analyzer);
        return this;
    }

    public MultiMatchExtQueryBuilder<T> slop(int slop) {
        this.multiMatchQueryBuilder.slop(slop);
        return this;
    }

    public MultiMatchExtQueryBuilder<T> fuzziness(String fuzziness) {
        this.multiMatchQueryBuilder.fuzziness(fuzziness);
        return this;
    }

    public MultiMatchExtQueryBuilder<T> fuzziness(int fuzziness) {
        this.multiMatchQueryBuilder.fuzziness(fuzziness);
        return this;
    }

    public MultiMatchExtQueryBuilder<T> prefixLength(int prefixLength) {
        this.multiMatchQueryBuilder.prefixLength(prefixLength);
        return this;
    }

    public MultiMatchExtQueryBuilder<T> tieBreaker(float tieBreaker) {
        this.multiMatchQueryBuilder.tieBreaker(tieBreaker);
        return this;
    }

    public MultiMatchExtQueryBuilder<T> cutoffFrequency(float cutoff) {
        this.multiMatchQueryBuilder.cutoffFrequency(cutoff);
        return this;
    }

    public MultiMatchExtQueryBuilder<T> fuzzyTranspositions(boolean fuzzyTranspositions) {
        this.multiMatchQueryBuilder.fuzzyTranspositions(fuzzyTranspositions);
        return this;
    }

    @Override
    public QueryBuilder build() {
        return this.multiMatchQueryBuilder;
    }
}
