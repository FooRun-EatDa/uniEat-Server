package com.foorun.unieat.domain.restaurant.jpo;

import com.foorun.unieat.domain.JsonSerializable;
import com.foorun.unieat.domain.file.jpo.BaseFileJpo;
import com.foorun.unieat.domain.file.jpo.FileJpo;
import lombok.*;

import javax.persistence.*;

@Getter
@Setter
@ToString(callSuper = true)
@NoArgsConstructor
@AllArgsConstructor(access = AccessLevel.PUBLIC)
@Entity
@Table(name = "restaurant_file")
@IdClass(RestaurantFileIdJpo.class)
public class RestaurantFileJpo extends BaseFileJpo implements JsonSerializable {
    @Id
    @ToString.Exclude
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "restaurant_id")
    private RestaurantJpo restaurant;

    @Builder
    protected RestaurantFileJpo(boolean thumbnail, int sequence, RestaurantJpo restaurant, FileJpo file) {
        super(thumbnail, sequence, file);
        this.restaurant = restaurant;
    }

    public static RestaurantFileJpo of(RestaurantJpo restaurant, FileJpo file, boolean thumbnail, int sequence) {
        return RestaurantFileJpo.builder()
                .restaurant(restaurant)
                .file(file)
                .thumbnail(thumbnail)
                .sequence(sequence)
                .build();
    }
}
