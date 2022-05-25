package com.foorun.unieat.domain.event.jpo;

import com.foorun.unieat.domain.BaseTimeJpo;
import com.foorun.unieat.domain.coupon.entity.CouponJpo;
import com.foorun.unieat.domain.event.EventStatus;
import com.foorun.unieat.domain.restaurant.jpo.RestaurantJpo;
import lombok.*;

import javax.persistence.*;
import java.util.HashSet;

@Getter
@Setter
@ToString(callSuper = true)
@NoArgsConstructor
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Builder
@Entity
@Table(name = "event")
public class EventJpo extends BaseTimeJpo {

    @Id
    @GeneratedValue
    private Long id;

    //이벤트 이미지 url
    private String imgUrl;

    //이벤트 이름
    private String name;

    private String expiredDate;

    @Column(columnDefinition = "TEXT")
    private String desc;

    private String notice;

    //이벤트 유효 여부(종료됨, 진행중)
    @Enumerated(value = EnumType.STRING)
    private EventStatus status;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "restaurant")
    private RestaurantJpo restaurant;

    @OneToMany(mappedBy = "event")
    @Builder.Default
    @ToString.Exclude
    private HashSet<CouponJpo> coupons = new HashSet<>();


}