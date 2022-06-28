package com.foorun.unieat.admin.domain;

import com.foorun.unieat.domain.restaurant.jpo.RestaurantBusinessHourJpo;
import lombok.*;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Builder
public class ARestaurantBusinessHour {
    private long restaurantId;
    private String content;
    private int sequence;

    public static ARestaurantBusinessHour of(RestaurantBusinessHourJpo restaurantBusinessHourJpo) {
        return ARestaurantBusinessHour.builder()
                .restaurantId(restaurantBusinessHourJpo.getId().getRestaurant().getId())
                .content(restaurantBusinessHourJpo.getId().getContent())
                .sequence(restaurantBusinessHourJpo.getSequence())
                .build();
    }
}
