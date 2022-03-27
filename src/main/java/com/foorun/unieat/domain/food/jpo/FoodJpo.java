package com.foorun.unieat.domain.food.jpo;

import com.foorun.unieat.domain.BaseTimeJpo;
import com.foorun.unieat.domain.common.StatusType;
import com.foorun.unieat.domain.restaurant.jpo.RestaurantJpo;
import lombok.*;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@ToString(callSuper = true)
@NoArgsConstructor
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Builder
@Entity
@Table(name = "food")
@DynamicUpdate
public class FoodJpo extends BaseTimeJpo {
    @Id
    @Column(name = "food_id")
    private Long id;

    /**
     * 메뉴명
     */
    private String name;

    /**
     * 메뉴 음식 이미지
     */
    @Column(name = "img_url")
    private String imgUrl;

    /**
     * 메뉴 가격
     */
    private int price;

    /**
     * 음식 상세 설명
     */
    private String content;

    /**
     * 표출 순서
     */
    private int sequence;

    /**
     * 상태
     */
    @Builder.Default
    @Enumerated(EnumType.STRING)
    private StatusType status = StatusType.ACTIVE;

    @ToString.Exclude
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "restaurant_id")
    private RestaurantJpo restaurant;

    @Builder.Default
    @ToString.Exclude
    @OneToMany(mappedBy = "food")
    private List<FoodFileJpo> files = new ArrayList<>();
}
