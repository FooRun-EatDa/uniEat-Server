package com.foorun.unieat.domain.event.jpo;

import com.foorun.unieat.domain.BaseTimeJpo;
import com.foorun.unieat.domain.coupon.entity.CouponJpo;
import com.foorun.unieat.domain.event.EventStatus;
import com.foorun.unieat.domain.restaurant.jpo.RestaurantJpo;
import com.foorun.unieat.util.converter.StringListConverter;
import lombok.*;

import javax.persistence.*;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

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
    @Column(name = "event_id")
    private Long id;

    //이벤트 이미지 url
    private String imgUrl;

    //이벤트 이름
    private String name;

    @Column(name = "expired_date")
    private String expiredDate;

    @Column(name = "`desc`", columnDefinition = "TEXT")
    private String desc;

    @Convert(converter = StringListConverter.class)
    @Column(columnDefinition = "JSON")
    private List<String> notice;

    //이벤트 유효 여부(종료됨, 진행중)
    @Enumerated(EnumType.STRING)
    private EventStatus status;

    @Column(columnDefinition = "BIGINT",name = "coupon_count")
    private Long couponCount;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "restaurant_id")
    private RestaurantJpo restaurant;

    @OneToMany(mappedBy = "event")
    @Builder.Default
    @ToString.Exclude
    private Set<CouponJpo> coupons = new HashSet<>();


    /**
     * 쿠폰 카운트 하나 차감
     */
    public void subtractCouponCountByOne(){
        this.couponCount -=1;
    }

}
