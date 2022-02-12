package com.foorun.unieat.domain;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.foorun.unieat.util.JsonUtil;

import java.io.Serializable;

@JsonInclude(JsonInclude.Include.NON_NULL)
public interface JsonSerializable extends Serializable {
    default String asJson() {
        return JsonUtil.asJson(this);
    }

    default String asPrettyJson() {
        return JsonUtil.asPrettyJson(this);
    }
}
