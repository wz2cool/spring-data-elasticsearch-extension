package com.github.wz2cool.elasticsearch.query.builder;

import com.github.wz2cool.elasticsearch.helper.CommonsHelper;
import com.github.wz2cool.elasticsearch.lambda.GetStringPropertyFunction;
import org.elasticsearch.index.query.QueryBuilder;
import org.elasticsearch.index.query.RegexpQueryBuilder;

/**
 * @see RegexpQueryBuilder
 */
public class RegexpExtQueryBuilder<T> implements ExtQueryBuilder {

    private final RegexpQueryBuilder regexpQueryBuilder;

    /**
     * @see RegexpQueryBuilder#RegexpQueryBuilder(String, String)
     */
    public RegexpExtQueryBuilder(GetStringPropertyFunction<T> getPropertyFunc, String regexp) {
        String propertyName = CommonsHelper.getPropertyName(getPropertyFunc);
        this.regexpQueryBuilder = new RegexpQueryBuilder(propertyName, regexp);
    }

    @Override
    public QueryBuilder build() {
        return this.regexpQueryBuilder;
    }
}
