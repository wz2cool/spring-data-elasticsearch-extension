package com.github.wz2cool.elasticsearch.query.builder;

import org.elasticsearch.index.query.QueryBuilder;

import java.math.BigDecimal;
import java.util.Date;

public interface ExtQueryBuilder {

    default Object getFilterValue(Object value) {
        if (value instanceof Date) {
            return ((Date) value).getTime();
        } else if (value instanceof BigDecimal) {
            return value.toString();
        }
        return value;
    }

    QueryBuilder build();
}
