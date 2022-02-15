package com.foorun.unieat.domain.hashtag.jpo;

import com.foorun.unieat.domain.restaurant.jpo.RestaurantJpo;
import lombok.*;

import javax.persistence.*;

@Getter
@Setter
@ToString(callSuper = true)
@NoArgsConstructor
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Entity
public class HashTagRestaurantJpo {

    @Id
    @Column(name = "HashTagRestaurant_id")
    private Long id;


    @ManyToOne(fetch = FetchType.LAZY)
    private HashTagJpo hashTag;

    @ManyToOne(fetch = FetchType.LAZY)
    private RestaurantJpo restaurant;



}
