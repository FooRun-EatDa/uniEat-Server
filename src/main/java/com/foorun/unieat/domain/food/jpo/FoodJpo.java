package com.foorun.unieat.domain.food.jpo;

import com.foorun.unieat.domain.BaseTimeJpo;
import lombok.*;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Getter @Setter
@Entity
@NoArgsConstructor
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class FoodJpo extends BaseTimeJpo {

    @Id
    @Column(name="food_id")
    private Long id;

    private String name;

    /**
     * 메뉴 음식 이미지
     */
    private String img_url;

    private int price;

    /**
     * 음식 상세 설명
     */
    private String content;

    private String status;

}
