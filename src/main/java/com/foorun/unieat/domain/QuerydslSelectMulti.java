package com.foorun.unieat.domain;

import org.springframework.data.domain.Pageable;

import java.util.List;

public interface QuerydslSelectMulti<T> {
    List<T> find(Pageable pageable);

    default List<T> findFetchJoin(Pageable pageable) {
        return find(pageable);
    }
}
