package com.foorun.unieat.domain.food.dto;

import com.foorun.unieat.domain.food.jpo.FoodJpo;
import lombok.*;
import org.springframework.beans.BeanUtils;



@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class Food {

    private Long id;

    private String name;

    private String img_url;
    private int price;

    private String content;
    private String status;




    public static Food createEmpty(){ return new Food();}

    public static Food of(FoodJpo foodJpo){

        Food food = createEmpty();
        BeanUtils.copyProperties(foodJpo, food);
        return food;
    }


    public FoodJpo asJpo(){
        FoodJpo foodJpo = new FoodJpo();
        BeanUtils.copyProperties(this,foodJpo);
        return foodJpo;

    }



}
