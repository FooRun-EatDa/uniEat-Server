package com.foorun.unieat.domain.category.jpo;

import com.foorun.unieat.domain.restaurant.jpo.RestaurantJpo;
import lombok.*;

import javax.persistence.*;

@Getter @Setter
@Entity
@ToString(callSuper = true)
@NoArgsConstructor
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class CategoryJpo {

    @Id
    @Column(name="category_id")
    private Long id;

    private String category_name;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="restaurant_id")
    @ToString.Exclude
    private RestaurantJpo restaurant;

}
