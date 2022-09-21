package com.foorun.unieat.domain.food.jpo;

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
@Table(name = "food_file")
@IdClass(FoodFileIdJpo.class)
public class FoodFileJpo extends BaseFileJpo implements JsonSerializable {
    @Id
    @ToString.Exclude
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "food_id")
    private FoodJpo food;

    @Builder
    protected FoodFileJpo(boolean thumbnail, int sequence, FoodJpo food, FileJpo file) {
        super(thumbnail, sequence, file);
        this.food = food;
    }

    public static FoodFileJpo of(FoodJpo food, FileJpo file, boolean thumbnail, int sequence) {
        return FoodFileJpo.builder()
                .food(food)
                .file(file)
                .thumbnail(thumbnail)
                .sequence(sequence)
                .build();
    }
}
