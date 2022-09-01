package com.foorun.unieat.domain.food.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.foorun.unieat.domain.file.dto.FileDetail;
import com.foorun.unieat.domain.food.jpo.FoodFileJpo;
import com.foorun.unieat.domain.food.jpo.FoodJpo;
import com.foorun.unieat.domain.restaurant.jpo.RestaurantJpo;
import com.foorun.unieat.util.IdentifyGenerator;
import lombok.*;
import org.springframework.beans.BeanUtils;

import java.time.LocalDateTime;
import java.util.List;

import static com.foorun.unieat.util.StreamUtil.map;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@ToString
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Food {
    @Builder.Default
    private Long id = IdentifyGenerator.number();
    private String name;
    private int price;
    private String content;
    private boolean newly;
    private boolean delete;
    private int sequence;
    private List<FileDetail> files;

    @JsonProperty("img_url")
    private String imgUrl;

    public static Food createEmpty() {
        return new Food();
    }

    public static Food of(FoodJpo foodJpo) {
        Food food = createEmpty();
        BeanUtils.copyProperties(foodJpo, food);
        food.files = map(foodJpo.getFiles(), FileDetail::of);
        return food;
    }

    public void applyRevision(FoodJpo foodJpo) {
        BeanUtils.copyProperties(this, foodJpo);
        foodJpo.setUpdatedAt(LocalDateTime.now());
        foodJpo.setFiles(map(files, fileDetail -> FoodFileJpo.of(foodJpo, fileDetail.asJpo(), fileDetail.isThumbnail(), fileDetail.getSequence())));
    }

    public FoodJpo asJpo(RestaurantJpo restaurantJpo) {
        FoodJpo foodJpo = new FoodJpo();
        foodJpo.setRestaurant(restaurantJpo);
        BeanUtils.copyProperties(this, foodJpo);
        return foodJpo;
    }
}
