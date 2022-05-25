package com.foorun.unieat.domain.event.jpo;

import com.foorun.unieat.domain.BaseTimeJpo;
import com.foorun.unieat.domain.restaurant.jpo.RestaurantJpo;
import lombok.*;

import javax.persistence.*;

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

    private String desc;

    private String notice;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "restaurant")
    private RestaurantJpo restaurant;

}
