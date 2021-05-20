package com.github.wz2cool.elasticsearch.query;

import com.github.wz2cool.elasticsearch.helper.CommonsHelper;
import com.github.wz2cool.elasticsearch.lambda.*;
import org.elasticsearch.index.query.*;

import java.util.Arrays;

public class QueryExtBuilders<T> {

    /**
     * A query that matches on all documents.
     */
    public MatchAllQueryBuilder matchAllQuery() {
        return QueryBuilders.matchAllQuery();
    }

    public MatchQueryBuilder matchQuery(GetStringPropertyFunction<T> getPropertyFunc, String value) {
        String propertyName = CommonsHelper.getPropertyName(getPropertyFunc);
        return QueryBuilders.matchQuery(propertyName, value);
    }

    public MultiMatchQueryExtBuilder<T> multiMatchQuery(String text, GetStringPropertyFunction<T>... getPropertyFuncs) {
        final String[] fieldNames = Arrays.stream(getPropertyFuncs).map(CommonsHelper::getPropertyName).toArray(String[]::new);
        return new MultiMatchQueryExtBuilder<>(text, fieldNames);
    }

    public MatchPhraseQueryBuilder matchPhraseQuery(GetStringPropertyFunction<T> getPropertyFunc, String text) {
        String propertyName = CommonsHelper.getPropertyName(getPropertyFunc);
        return new MatchPhraseQueryBuilder(propertyName, text);
    }

    public static DisMaxQueryBuilder disMaxQuery() {
        return new DisMaxQueryBuilder();
    }

    public static IdsQueryBuilder idsQuery() {
        return new IdsQueryBuilder();
    }

    public static IdsQueryBuilder idsQuery(String... types) {
        return new IdsQueryBuilder().types(types);
    }

    public MatchPhrasePrefixQueryBuilder matchPhrasePrefixQuery(GetStringPropertyFunction<T> getPropertyFunc, String text) {
        String propertyName = CommonsHelper.getPropertyName(getPropertyFunc);
        return new MatchPhrasePrefixQueryBuilder(propertyName, text);
    }

    /// region termQuery

    public TermQueryBuilder termQueryBuilder(GetStringPropertyFunction<T> getPropertyFunc, String value) {
        String propertyName = CommonsHelper.getPropertyName(getPropertyFunc);
        return new TermQueryBuilder(propertyName, value);
    }

    public TermQueryBuilder termQueryBuilder(GetIntegerPropertyFunction<T> getPropertyFunc, int value) {
        String propertyName = CommonsHelper.getPropertyName(getPropertyFunc);
        return new TermQueryBuilder(propertyName, value);
    }

    public TermQueryBuilder termQueryBuilder(GetLongPropertyFunction<T> getPropertyFunc, long value) {
        String propertyName = CommonsHelper.getPropertyName(getPropertyFunc);
        return new TermQueryBuilder(propertyName, value);
    }

    public TermQueryBuilder termQueryBuilder(GetFloatPropertyFunction<T> getPropertyFunc, float value) {
        String propertyName = CommonsHelper.getPropertyName(getPropertyFunc);
        return new TermQueryBuilder(propertyName, value);
    }

    public TermQueryBuilder termQueryBuilder(GetDoublePropertyFunction<T> getPropertyFunc, double value) {
        String propertyName = CommonsHelper.getPropertyName(getPropertyFunc);
        return new TermQueryBuilder(propertyName, value);
    }

    public TermQueryBuilder termQueryBuilder(GetBooleanPropertyFunction<T> getPropertyFunc, boolean value) {
        String propertyName = CommonsHelper.getPropertyName(getPropertyFunc);
        return new TermQueryBuilder(propertyName, value);
    }

    /// endregion

    public FuzzyQueryBuilder fuzzyQuery(GetStringPropertyFunction<T> getPropertyFunc, String value) {
        String propertyName = CommonsHelper.getPropertyName(getPropertyFunc);
        return new FuzzyQueryBuilder(propertyName, value);
    }

    public PrefixQueryBuilder prefixQuery(GetStringPropertyFunction<T> getPropertyFunc, String prefix) {
        String propertyName = CommonsHelper.getPropertyName(getPropertyFunc);
        return new PrefixQueryBuilder(propertyName, prefix);
    }
}
