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
@Table(name = "hashtag_restaurant")
public class HashTagRestaurantJpo {

    @Id
    private Long id;


    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "hashtag_id")
    private HashTagJpo hashTag;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "hastag_restaurant_id")
    private RestaurantJpo restaurant;



}
