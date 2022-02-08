package com.foorun.unieat.domain;

import com.foorun.unieat.util.JsonUtil;

import java.io.Serializable;

public interface JsonSerializable extends Serializable {
    default String asJson() {
        return JsonUtil.asJson(this);
    }

    default String asPrettyJson() {
        return JsonUtil.asPrettyJson(this);
    }
}
