package com.github.wz2cool.elasticsearch.lambda;

import java.io.Serializable;
import java.util.function.Function;

@FunctionalInterface
public interface GetArrayPropertyFunction<T, R extends Comparable> extends Function<T, R[]>, Serializable {
}
