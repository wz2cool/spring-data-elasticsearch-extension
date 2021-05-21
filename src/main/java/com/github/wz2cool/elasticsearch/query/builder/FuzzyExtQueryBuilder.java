package com.github.wz2cool.elasticsearch.query.builder;

import com.github.wz2cool.elasticsearch.helper.CommonsHelper;
import com.github.wz2cool.elasticsearch.lambda.GetStringPropertyFunction;
import org.elasticsearch.index.query.FuzzyQueryBuilder;
import org.elasticsearch.index.query.QueryBuilder;

/**
 * A Query that does fuzzy matching for a specific value.
 *
 * @author Frank
 */
public class FuzzyExtQueryBuilder<T> implements ExtQueryBuilder {

    private FuzzyQueryBuilder fuzzyQueryBuilder;

    /**
     * @see FuzzyQueryBuilder#FuzzyQueryBuilder(String, Object)
     */
    public FuzzyExtQueryBuilder(GetStringPropertyFunction<T> getPropertyFunc, String value) {
        String propertyName = CommonsHelper.getPropertyName(getPropertyFunc);
        this.fuzzyQueryBuilder = new FuzzyQueryBuilder(propertyName, value);
    }

    @Override
    public QueryBuilder build() {
        return this.fuzzyQueryBuilder;
    }
}
