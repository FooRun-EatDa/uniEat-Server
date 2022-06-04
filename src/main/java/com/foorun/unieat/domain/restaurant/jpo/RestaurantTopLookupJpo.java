package com.foorun.unieat.domain.restaurant.jpo;


import com.foorun.unieat.domain.BaseTimeJpo;
import com.foorun.unieat.domain.code.region.jpo.RegionCodeJpo;
import lombok.*;

import javax.persistence.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Entity
@Builder
@ToString(callSuper = true)
@Table(name ="restaurant_best")
public class RestaurantTopLookupJpo extends BaseTimeJpo {

    @Id
    private Long id;


    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "restaurant_id")
    @ToString.Exclude
    private RestaurantJpo restaurant;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "region_id")
    @ToString.Exclude
    private RegionCodeJpo regionCode;


}
