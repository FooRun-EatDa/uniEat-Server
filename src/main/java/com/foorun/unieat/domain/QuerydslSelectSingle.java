package com.foorun.unieat.domain;

import java.util.Optional;

public interface QuerydslSelectSingle<T, ID> {
    Optional<T> find(ID id);
}
