package com.github.wz2cool.elasticsearch.query.builder;

import com.github.wz2cool.elasticsearch.lambda.*;

import java.util.Date;

public class ExtQueryBuilders<T> {


    public MultiMatchExtQueryBuilder<T> multiMatchQuery(String value) {
        return new MultiMatchExtQueryBuilder<>(value);
    }

    public MatchPhraseExtQueryBuilder<T> matchPhraseQueryBuilder(GetStringPropertyFunction<T> getPropertyFunc, String text) {
        return new MatchPhraseExtQueryBuilder<>(getPropertyFunc, text);
    }

    public MatchPhrasePrefixExtQueryBuilder<T> matchPhrasePrefixQuery(GetStringPropertyFunction<T> getPropertyFunc, String text) {
        return new MatchPhrasePrefixExtQueryBuilder<>(getPropertyFunc, text);
    }

    public TermExtQueryBuilder<T, String> termQuery(GetStringPropertyFunction<T> getPropertyFunc, String value) {
        return new TermExtQueryBuilder<>(getPropertyFunc, value);
    }

    public TermExtQueryBuilder<T, Integer> termQuery(GetIntegerPropertyFunction<T> getPropertyFunc, int value) {
        return new TermExtQueryBuilder<>(getPropertyFunc, value);
    }

    public TermExtQueryBuilder<T, Long> termQuery(GetLongPropertyFunction<T> getPropertyFunc, long value) {
        return new TermExtQueryBuilder<>(getPropertyFunc, value);
    }

    public TermExtQueryBuilder<T, Float> termQuery(GetFloatPropertyFunction<T> getPropertyFunc, float value) {
        return new TermExtQueryBuilder<>(getPropertyFunc, value);
    }

    public TermExtQueryBuilder<T, Double> termQuery(GetDoublePropertyFunction<T> getPropertyFunc, double value) {
        return new TermExtQueryBuilder<>(getPropertyFunc, value);
    }

    public TermExtQueryBuilder<T, Boolean> termQuery(GetBooleanPropertyFunction<T> getPropertyFunc, boolean value) {
        return new TermExtQueryBuilder<>(getPropertyFunc, value);
    }

    public TermExtQueryBuilder<T, Date> termQuery(GetDatePropertyFunction<T> getPropertyFunc, Date value) {
        return new TermExtQueryBuilder<>(getPropertyFunc, value);
    }

    public FuzzyExtQueryBuilder<T> fuzzyQuery(GetStringPropertyFunction<T> getPropertyFunc, String value) {
        return new FuzzyExtQueryBuilder<>(getPropertyFunc, value);
    }

    public PrefixExtQueryBuilder<T> prefixQuery(GetStringPropertyFunction<T> getPropertyFunc, String prefix) {
        return new PrefixExtQueryBuilder<>(getPropertyFunc, prefix);
    }

    public RangeExtQueryBuilder<T, Integer> rangeQuery(GetIntegerPropertyFunction<T> getPropertyFunc) {
        return new RangeExtQueryBuilder<>(getPropertyFunc);
    }

    public RangeExtQueryBuilder<T, Long> rangeQuery(GetLongPropertyFunction<T> getPropertyFunc) {
        return new RangeExtQueryBuilder<>(getPropertyFunc);
    }

    public RangeExtQueryBuilder<T, Float> rangeQuery(GetFloatPropertyFunction<T> getPropertyFunc) {
        return new RangeExtQueryBuilder<>(getPropertyFunc);
    }

    public RangeExtQueryBuilder<T, Double> rangeQuery(GetDoublePropertyFunction<T> getPropertyFunc) {
        return new RangeExtQueryBuilder<>(getPropertyFunc);
    }

    public RangeExtQueryBuilder<T, Date> rangeQuery(GetDatePropertyFunction<T> getPropertyFunc) {
        return new RangeExtQueryBuilder<>(getPropertyFunc);
    }

    public WildcardExtQueryBuilder<T> wildcardQuery(GetStringPropertyFunction<T> getPropertyFunc, String query) {
        return new WildcardExtQueryBuilder<>(getPropertyFunc, query);
    }

    public RegexpExtQueryBuilder<T> regexpQuery(GetStringPropertyFunction<T> getPropertyFunc, String regexp) {
        return new RegexpExtQueryBuilder<>(getPropertyFunc, regexp);
    }
}
