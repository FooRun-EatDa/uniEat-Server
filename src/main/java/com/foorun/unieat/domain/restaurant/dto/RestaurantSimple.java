package com.foorun.unieat.domain.restaurant.dto;


import lombok.*;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Builder
public class RestaurantSimple {
    private Long id;
    private String name;
    private String explanation;
    private String imgUrl;
}
