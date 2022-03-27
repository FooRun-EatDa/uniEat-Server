package com.foorun.unieat.domain.restaurant.jpo;

import com.foorun.unieat.domain.JsonSerializable;
import lombok.*;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor(access = AccessLevel.PROTECTED)
public class RestaurantFileIdJpo implements JsonSerializable {
    private Long restaurant;
    private String file;

    public static RestaurantFileIdJpo of(long restaurantId, String fileId) {
        return new RestaurantFileIdJpo(restaurantId, fileId);
    }
}
