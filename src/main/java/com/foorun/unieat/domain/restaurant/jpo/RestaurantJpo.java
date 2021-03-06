package com.foorun.unieat.domain.restaurant.jpo;

import com.foorun.unieat.domain.category.jpo.CategoryJpo;
import com.foorun.unieat.domain.code.region.jpo.RegionCodeJpo;
import com.foorun.unieat.domain.feeling.jpo.ReviewFeelingJpo;
import com.foorun.unieat.domain.food.jpo.FoodJpo;
import com.foorun.unieat.domain.hashtag.jpo.HashTagRestaurantJpo;
import com.foorun.unieat.domain.review.jpo.ReviewJpo;
import lombok.*;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Getter @Setter
@NoArgsConstructor
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Entity
@Builder
@ToString(callSuper = true)
@Table(name ="restaurant")
public class RestaurantJpo {
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
    private String imguUrl;

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

    private String status;


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
    private Set<CategoryJpo> categorys = new HashSet<>();

    @OneToMany
    @JoinColumn(name="food_id")
    @ToString.Exclude
    private Set<FoodJpo> foods = new HashSet<>();


    @OneToMany
    @JoinColumn(name="review_id")
    @ToString.Exclude
    private Set<ReviewJpo> reviews = new HashSet<>();

    @OneToMany(mappedBy = "restaurant")
    @Column(name="hash_tag_restaurnat_id")
    @ToString.Exclude
    private Set<HashTagRestaurantJpo> hashTagRestaurants = new HashSet<>();






}
