package com.foorun.unieat.domain.food.jpo;

import com.foorun.unieat.domain.JsonSerializable;
import lombok.*;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor(access = AccessLevel.PROTECTED)
public class FoodFileIdJpo implements JsonSerializable {
    private Long food;
    private String file;

    public static FoodFileIdJpo of(Long foodId, String fileId) {
        return new FoodFileIdJpo(foodId, fileId);
    }
}
