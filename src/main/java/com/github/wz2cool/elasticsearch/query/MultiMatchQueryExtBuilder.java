package com.github.wz2cool.elasticsearch.query;

import com.github.wz2cool.elasticsearch.helper.CommonsHelper;
import com.github.wz2cool.elasticsearch.lambda.GetCommonPropertyFunction;
import org.elasticsearch.common.io.stream.StreamInput;
import org.elasticsearch.index.query.MultiMatchQueryBuilder;

import java.io.IOException;

public class MultiMatchQueryExtBuilder<T> extends MultiMatchQueryBuilder {

    public MultiMatchQueryExtBuilder(Object value, String... fields) {
        super(value, fields);
    }

    public MultiMatchQueryExtBuilder(StreamInput in) throws IOException {
        super(in);
    }

    public MultiMatchQueryExtBuilder<T> field(GetCommonPropertyFunction<T> getPropertyFunc) {
        final String propertyName = CommonsHelper.getPropertyName(getPropertyFunc);
        field(propertyName);
        return this;
    }

    public MultiMatchQueryExtBuilder<T> field(GetCommonPropertyFunction<T> getPropertyFunc, float boost) {
        final String propertyName = CommonsHelper.getPropertyName(getPropertyFunc);
        field(propertyName, boost);
        return this;
    }
}
