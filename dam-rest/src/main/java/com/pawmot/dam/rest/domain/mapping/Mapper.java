package com.pawmot.dam.rest.domain.mapping;

import java.util.Collection;
import java.util.stream.Collectors;

public interface Mapper<T, R> {
    R map(T t);

    default Collection<R> mapCollection(Collection<? extends T> ts) {
        return ts.stream().map(this::map).collect(Collectors.toList());
    }
}
