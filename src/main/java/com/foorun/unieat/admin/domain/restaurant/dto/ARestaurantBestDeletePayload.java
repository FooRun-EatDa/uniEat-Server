package com.foorun.unieat.admin.domain.restaurant.dto;

import lombok.*;

import java.util.List;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class ARestaurantBestDeletePayload {
    private List<Long> restaurantIds;
}
