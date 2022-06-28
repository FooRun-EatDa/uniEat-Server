package com.foorun.unieat.domain.restaurant.jpo;

import lombok.*;

import javax.persistence.*;
import java.io.Serializable;

@Getter
@Setter
@ToString(callSuper = true)
@NoArgsConstructor
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Entity
@Table(name = "restaurant_business_hour")
public class RestaurantBusinessHourJpo {
    @EmbeddedId
    private Identify id;
    private int sequence;

    @Getter
    @Setter
    @ToString(callSuper = true)
    @NoArgsConstructor
    @AllArgsConstructor(access = AccessLevel.PRIVATE)
    @Embeddable
    public static class Identify implements Serializable {
        @ToString.Exclude
        @ManyToOne(fetch = FetchType.LAZY)
        @JoinColumn(name = "restaurant_id")
        private RestaurantJpo restaurant;
        private String content;
    }
}
