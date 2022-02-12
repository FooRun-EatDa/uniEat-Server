package com.foorun.unieat.domain.restaurant.jpo;

import com.foorun.unieat.domain.code.region.jpo.RegionCodeJpo;
import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;

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
    private String img_url;

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
    private String phone_number;

    /**
     * 운영 시간
     */
    private String operation_time;

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
    @ManyToOne
    @JoinColumn(name ="region_code")
    @ToString.Exclude
    private RegionCodeJpo regionCode;


//    @OneToMany
//    @JoinColumn(name="food_id")
//    @ToString.Exclude
//    private List<FoodJpo> foods = new ArrayList<>();
//





}
