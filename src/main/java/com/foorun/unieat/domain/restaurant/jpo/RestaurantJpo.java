package com.foorun.unieat.domain.restaurant.jpo;

import com.foorun.unieat.domain.BaseTimeJpo;
import com.foorun.unieat.domain.bookmark.jpo.BookmarkJpo;
import com.foorun.unieat.domain.category.jpo.CategoryJpo;
import com.foorun.unieat.domain.code.region.jpo.RegionCodeJpo;
import com.foorun.unieat.domain.common.StatusType;
import com.foorun.unieat.domain.event.jpo.EventJpo;
import com.foorun.unieat.domain.food.jpo.FoodJpo;
import com.foorun.unieat.domain.hashtag.jpo.HashTagRestaurantJpo;
import com.foorun.unieat.domain.review.jpo.ReviewJpo;
import lombok.*;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

@Getter @Setter
@NoArgsConstructor
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Entity
@Builder
@ToString(callSuper = true)
@Table(name ="restaurant")
public class RestaurantJpo extends BaseTimeJpo {
    @Id
    @Column(name = "restaurant_id")
    private Long id;

    /**
     * 식당 이름
     */
    private String name;


    /**
     * 식당 관련 짧은 소개
     */
    private String explanation;

    /**
     * 식당 이미지
     */
    @Column(name="img_url")
    private String imgUrl;

    /**
     * 식당 상세 설명
     */
    private String content;

    /**
     * 식당 주소
     */
    private String address;

    /**
     * 경도
     */
    private String longitude;

    /**
     * 위도
     */
    private String latitude;

    /**
     * 전화 번호
     */
    @Column(name="phone_number")
    private String phoneNumber;

    /**
     * 운영 시간
     */
    @Column(name="operation_time")
    private String operationTime;

    /**
     * 식당 대표 음식 가격
     */
    private int price;

    /**
     * 학생들이 부르는 지역을 기준으로 해당 식당이 속한 위치
     */
    private String district;

    @Builder.Default
    @Enumerated(EnumType.STRING)
    private StatusType status = StatusType.ACTIVE;

    /**
     * 식당의 행정적 위치 정보
     */
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name ="code_id")
    @ToString.Exclude
    private RegionCodeJpo regionCode;

    @OneToMany
    @JoinColumn(name="category_id")
    @ToString.Exclude
    @Builder.Default
    private Set<CategoryJpo> categorys = new HashSet<>();

    @ToString.Exclude
    @Builder.Default
    @OneToMany
    @JoinColumn(name = "restaurant_id")
    private Set<FoodJpo> foods = new HashSet<>();

    @ToString.Exclude
    @Builder.Default
    @OneToMany(mappedBy = "restaurant")
    private Set<RestaurantFileJpo> files = new HashSet<>();

    @OneToMany
    @JoinColumn(name="review_id")
    @ToString.Exclude
    @Builder.Default
    private Set<ReviewJpo> reviews = new HashSet<>();

    @OneToMany(mappedBy = "restaurant")
    @ToString.Exclude
    @Builder.Default
    private Set<HashTagRestaurantJpo> hashTagRestaurants = new HashSet<>();


    @OneToMany(mappedBy = "restaurant")
    @ToString.Exclude
    @Builder.Default
    private Set<BookmarkJpo> bookmark = new HashSet<>();


    @OneToMany(mappedBy = "restaurant")
    @ToString.Exclude
    @Builder.Default
    private Set<RestaurantBestJpo> bestRestaurants = new HashSet<>();





    public Optional<FoodJpo> getFoodById(long id) {
        return foods.stream()
                .filter(foodJpo -> foodJpo.getId() == id)
                .findFirst();
    }


    //하나의 식당이 여러개의 이벤트를 할 수 있음
    @OneToMany(mappedBy = "restaurant")
    @ToString.Exclude
    @Builder.Default
    private Set<EventJpo> events = new HashSet<>();


}
