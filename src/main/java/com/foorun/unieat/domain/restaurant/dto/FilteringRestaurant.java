package com.foorun.unieat.domain.restaurant.dto;

import com.fasterxml.jackson.annotation.JsonInclude;


import lombok.*;

import java.util.List;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
public class FilteringRestaurant {

    private List<String> hashTags;
    private List<String> categories;
    private List<String> regions;
    //가격
    private List<Integer> prices;

}
