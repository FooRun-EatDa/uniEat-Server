package com.foorun.unieat.domain.kakao.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.List;

@Getter
@Setter
@ToString
public class KakaoLocalApiResponse {
    private List<Document> documents;

    @Getter
    @Setter
    @ToString
    public static class Document {
        @JsonProperty("address_name")
        private String addressName;

        @JsonProperty("address_type")
        private String addressType;

        private double x;
        private double y;
    }
}
